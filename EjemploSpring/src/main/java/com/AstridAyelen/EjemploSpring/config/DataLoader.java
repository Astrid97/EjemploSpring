//package com.AstridAyelen.EjemploSpring.config;
//
//import com.AstridAyelen.EjemploSpring.dtos.categoria.CategoriaCreate;
//import com.AstridAyelen.EjemploSpring.dtos.producto.ProductoCreate;
//import com.AstridAyelen.EjemploSpring.dtos.usuario.UsuarioCreate;
//import com.AstridAyelen.EjemploSpring.entity.Categoria;
//import com.AstridAyelen.EjemploSpring.entity.DetallePedido;
//import com.AstridAyelen.EjemploSpring.entity.Pedido;
//import com.AstridAyelen.EjemploSpring.entity.Producto;
//import com.AstridAyelen.EjemploSpring.entity.Usuario;
//import com.AstridAyelen.EjemploSpring.enums.Estado;
//import com.AstridAyelen.EjemploSpring.enums.FormaPago;
//import com.AstridAyelen.EjemploSpring.enums.Rol;
//import com.AstridAyelen.EjemploSpring.repository.CategoriaRepository;
//import com.AstridAyelen.EjemploSpring.repository.PedidoRepository;
//import com.AstridAyelen.EjemploSpring.repository.ProductoRepository;
//import com.AstridAyelen.EjemploSpring.repository.UsuarioRepository;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDate;
//import java.util.ArrayList;
//
//@Component
//@RequiredArgsConstructor
//public class DataLoader implements CommandLineRunner {
//
//    private final UsuarioRepository usuarioRepository;
//    private final ProductoRepository productoRepository;
//    private final CategoriaRepository categoriaRepository;
//    private final PedidoRepository pedidoRepository;
//
//    @Override
//    @Transactional
//    public void run(String... args) throws Exception {
//        System.out.println("--- INICIANDO CARGA DE DATOS DESDE DTOs ---");
//
//        // ==========================================
//        // A) 2 USUARIOS (Instanciados desde DTO)
//        // ==========================================
//        UsuarioCreate dtoUser1 = new UsuarioCreate("Pedro", "Perez", "Pedritope@admin.com", "1156559988", "admin123", Rol.ADMIN);
//        Usuario u1 = usuarioRepository.save(dtoUser1.toEntity()); // Convertimos y guardamos
//
//        UsuarioCreate dtoUser2 = new UsuarioCreate("Carla", "Gonzales", "carlyglz@cliente.com", "1122334455", "cliente123", Rol.USUARIO);
//        Usuario u2 = usuarioRepository.save(dtoUser2.toEntity());
//
//
//        // ==========================================
//        // D) 10 PRODUCTOS (Instanciados desde DTO)
//        // ==========================================
//        Producto p1 = productoRepository.save(new ProductoCreate("Whey Protein 1kg", 15000.0, "Sabor Vainilla", 50, "whey.jpg", true).toEntity());
//        Producto p2 = productoRepository.save(new ProductoCreate("Creatina 300g", 22000.0, "Monohidratada", 30, "crea.jpg", true).toEntity());
//        Producto p3 = productoRepository.save(new ProductoCreate("BCAA 500g", 12000.0, "Aminoácidos ramificados", 20, "bcaa.jpg", true).toEntity());
//        Producto p4 = productoRepository.save(new ProductoCreate("Pre-Workout", 18000.0, "Pre entreno explosivo", 15, "pre.jpg", true).toEntity());
//        Producto p5 = productoRepository.save(new ProductoCreate("Glutamina", 14000.0, "Recuperador nocturno", 25, "gluta.jpg", true).toEntity());
//        Producto p6 = productoRepository.save(new ProductoCreate("Multivitamínico", 8000.0, "60 cápsulas", 100, "multi.jpg", true).toEntity());
//        Producto p7 = productoRepository.save(new ProductoCreate("Omega 3", 9500.0, "Aceite de pescado", 80, "omega.jpg", true).toEntity());
//        Producto p8 = productoRepository.save(new ProductoCreate("Colágeno Hidrolizado", 16000.0, "Con Vitamina C", 40, "colageno.jpg", true).toEntity());
//        Producto p9 = productoRepository.save(new ProductoCreate("Shaker", 3500.0, "Vaso mezclador 600ml", 200, "shaker.jpg", true).toEntity());
//        Producto p10 = productoRepository.save(new ProductoCreate("Barra Proteica", 1200.0, "Sabor chocolate (Unidad)", 500, "barra.jpg", true).toEntity());
//
//
//        // ==========================================
//        // C) 3 CATEGORÍAS (Instanciadas desde DTO)
//        // ==========================================
//        Categoria c1 = new CategoriaCreate("Proteínas y Aminoácidos", "Recuperación muscular").toEntity();
//        c1.setProductos(new ArrayList<>());
//        c1.getProductos().add(p1);
//        c1.getProductos().add(p3);
//        c1.getProductos().add(p5);
//        categoriaRepository.save(c1);
//
//        Categoria c2 = new CategoriaCreate("Energía y Rendimiento", "Para darlo todo en el gym").toEntity();
//        c2.setProductos(new ArrayList<>());
//        c2.getProductos().add(p2);
//        c2.getProductos().add(p4);
//        categoriaRepository.save(c2);
//
//        Categoria c3 = new CategoriaCreate("Salud y Accesorios", "Bienestar general y equipamiento").toEntity();
//        c3.setProductos(new ArrayList<>());
//        c3.getProductos().add(p6);
//        c3.getProductos().add(p7);
//        c3.getProductos().add(p8);
//        c3.getProductos().add(p9);
//        c3.getProductos().add(p10);
//        categoriaRepository.save(c3);
//
//
//        // ==========================================
//        // B) 3 PEDIDOS (Con al menos 2 detalles c/u)
//        // ==========================================
//
//        if (u1.getPedidos() == null) u1.setPedidos(new ArrayList<>());
//        if (u2.getPedidos() == null) u2.setPedidos(new ArrayList<>());
//
//        // PEDIDO 1 (Del usuario 1)
//        Pedido ped1 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.PENDIENTE).formaPago(FormaPago.EFECTIVO).detalles(new ArrayList<>()).build();
//        ped1.getDetalles().add(DetallePedido.builder().producto(p1).cantidad(2).subtotal(p1.getPrecio() * 2).build());
//        ped1.getDetalles().add(DetallePedido.builder().producto(p9).cantidad(1).subtotal(p9.getPrecio() * 1).build());
//
//        ped1.calcularTotal(); // 1. Ejecutamos tu interfaz Calculable manualmente
//        Pedido ped1Guardado = pedidoRepository.save(ped1); // 2. Guardamos con el Repositorio (nos devuelve la Entidad)
//        u1.getPedidos().add(ped1Guardado); // 3. ¡Ahora sí encajan los tipos perfectamente!
//
//        // PEDIDO 2 (Del usuario 2)
//        Pedido ped2 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.CONFIRMADO).formaPago(FormaPago.TRANSFERENCIA).detalles(new ArrayList<>()).build();
//        ped2.getDetalles().add(DetallePedido.builder().producto(p2).cantidad(1).subtotal(p2.getPrecio() * 1).build());
//        ped2.getDetalles().add(DetallePedido.builder().producto(p10).cantidad(10).subtotal(p10.getPrecio() * 10).build());
//
//        ped2.calcularTotal();
//        Pedido ped2Guardado = pedidoRepository.save(ped2);
//        u2.getPedidos().add(ped2Guardado);
//
//        // PEDIDO 3 (Del usuario 2)
//        Pedido ped3 = Pedido.builder().fecha(LocalDate.now()).estado(Estado.TERMINADO).formaPago(FormaPago.EFECTIVO).detalles(new ArrayList<>()).build();
//        ped3.getDetalles().add(DetallePedido.builder().producto(p4).cantidad(1).subtotal(p4.getPrecio() * 1).build());
//        ped3.getDetalles().add(DetallePedido.builder().producto(p6).cantidad(2).subtotal(p6.getPrecio() * 2).build());
//        ped3.getDetalles().add(DetallePedido.builder().producto(p8).cantidad(1).subtotal(p8.getPrecio() * 1).build());
//
//        ped3.calcularTotal();
//        Pedido ped3Guardado = pedidoRepository.save(ped3);
//        u2.getPedidos().add(ped3Guardado);
//
//        // Guardamos los usuarios para que se actualicen las claves foráneas
//        usuarioRepository.save(u1);
//        usuarioRepository.save(u2);
//
//        System.out.println("--- ✅ CONSIGNAS DEL TP COMPLETADAS Y GUARDADAS EN H2 ---");
//    }
//}