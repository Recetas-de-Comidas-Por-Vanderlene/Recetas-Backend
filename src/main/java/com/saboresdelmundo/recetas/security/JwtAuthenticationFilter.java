package com.saboresdelmundo.recetas.security;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.saboresdelmundo.recetas.model.Usuario;
import com.saboresdelmundo.recetas.repository.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            // Verificamos primero si es una ruta pública que no necesita autenticación
            String path = request.getRequestURI();
            if (path.startsWith("/api/auth/")) {
                // Solo rutas de auth son completamente públicas
                filterChain.doFilter(request, response);
                return;
            }

            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                // No hay token, continuar sin autenticación
                filterChain.doFilter(request, response);
                return;
            }

            String token = authHeader.substring(7);
            String email = null;

            try {
                email = jwtUtil.getEmailFromToken(token);

                if (email != null && jwtUtil.validateToken(token)) {
                    // Buscar el usuario en la base de datos para obtener su rol
                    Usuario usuario = usuarioRepository.findByEmail(email).orElse(null);
                    if (usuario != null) {
                        // Crear una lista de autoridades con el rol del usuario
                        String rol = usuario.getRol();
                        System.out.println("Usuario: " + usuario.getEmail() + ", Rol: " + rol);

                        List<SimpleGrantedAuthority> authorities = List.of(
                                new SimpleGrantedAuthority(rol));

                        // Crear token de autenticación con las autoridades del usuario
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                                email, null, authorities);
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        SecurityContextHolder.getContext().setAuthentication(authentication);

                        System.out.println("Autenticación establecida con autoridades: " + authorities);
                    }
                }
            } catch (Exception e) {
                // Si hay un error al procesar el token, continuamos sin autenticación
                System.out.println("Error al procesar el token: " + e.getMessage());
            }

            filterChain.doFilter(request, response);
        } catch (Exception e) {
            // Si ocurre cualquier error, simplemente continuamos sin autenticación
            filterChain.doFilter(request, response);
        }
    }
}
