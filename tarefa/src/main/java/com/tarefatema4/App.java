package com.tarefatema4;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.tarefatema4.entity.Autor;
import com.tarefatema4.entity.Categoria;
import com.tarefatema4.entity.Editorial;
import com.tarefatema4.entity.Libro;
import com.tarefatema4.service.AutorService;
import com.tarefatema4.service.CategoriaService;
import com.tarefatema4.service.EditorialService;
import com.tarefatema4.service.LibroService;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner run(LibroService libroService, AutorService autorService,
                                  EditorialService editorialService, CategoriaService categoriaService) {
        return args -> {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            System.out.println("\n========== XESTIÓN DE LIBRERÍA ==========\n");

            //  1. CREAR CATEGORÍAS 
            System.out.println("1. Creando categorías");
            Categoria catFiccion = categoriaService.crearCategoria("Ficción", "Novelas e relatos de ficción");
            Categoria catCiencia = categoriaService.crearCategoria("Ciencia", "Libros científicos e técnicos");
            Categoria catHistoria = categoriaService.crearCategoria("Historia", "Historia e biografías");
            System.out.println(catFiccion);
            System.out.println(catCiencia);
            System.out.println(catHistoria);
            System.out.println();

            // 2. CREAR EDITORIALES 
            System.out.println("2. Creando editoriales");
            Editorial editAnagrama = editorialService.crearEditorial("Anagrama", "España");
            Editorial editPenguin  = editorialService.crearEditorial("Penguin Books", "Reino Unido");
            Editorial editPlanet   = editorialService.crearEditorial("Planeta", "España");
            System.out.println(editAnagrama);
            System.out.println(editPenguin);
            System.out.println(editPlanet);
            System.out.println();

            // 3. CREAR AUTORES
            System.out.println("3. Creando autores");
            Autor autorGarcia  = autorService.crearAutor("Gabriel", "García Márquez", sdf.parse("1927-03-06"));
            Autor autorOrwell  = autorService.crearAutor("George",  "Orwell",          sdf.parse("1903-06-25"));
            Autor autorHawking = autorService.crearAutor("Stephen", "Hawking",         sdf.parse("1942-01-08"));
            Autor autorTolstoi = autorService.crearAutor("Lev",     "Tolstói",         sdf.parse("1828-09-09"));
            Autor autorAsimov  = autorService.crearAutor("Isaac",   "Asimov",          sdf.parse("1920-01-02"));
            System.out.println(autorGarcia);
            System.out.println(autorOrwell);
            System.out.println(autorHawking);
            System.out.println(autorTolstoi);
            System.out.println(autorAsimov);
            System.out.println();

            // 4. CREAR LIBROS
            System.out.println("4. Creando libros");
            Libro libro1 = libroService.crearLibro("978-8433920638", "Cien años de soledad",            1967, 18.50f, editAnagrama.getId(), catFiccion.getId());
            Libro libro2 = libroService.crearLibro("978-8437604947", "1984",                             1949, 12.00f, editPenguin.getId(),  catFiccion.getId());
            Libro libro3 = libroService.crearLibro("978-8484326922", "Una breve historia del tiempo",    1988, 15.00f, editPlanet.getId(),   catCiencia.getId());
            Libro libro4 = libroService.crearLibro("978-8420604947", "Guerra y paz",                     1869, 22.00f, editAnagrama.getId(), catHistoria.getId());
            Libro libro5 = libroService.crearLibro("978-8497940234", "Fundación",                        1951, 14.00f, editPenguin.getId(),  catCiencia.getId());
            Libro libro6 = libroService.crearLibro("978-8433901234", "El amor en los tiempos del cólera", 1985, 17.00f, editAnagrama.getId(), catFiccion.getId());
            System.out.println(libro1.getTitulo());
            System.out.println(libro2.getTitulo());
            System.out.println(libro3.getTitulo());
            System.out.println(libro4.getTitulo());
            System.out.println(libro5.getTitulo());
            System.out.println(libro6.getTitulo());
            System.out.println();

            // 5. ASIGNAR AUTORES A LIBROS
            System.out.println("5. Asignando autores a libros");
            libroService.agregarAutor(libro1.getId(), autorGarcia.getId());
            libroService.agregarAutor(libro2.getId(), autorOrwell.getId());
            libroService.agregarAutor(libro3.getId(), autorHawking.getId());
            libroService.agregarAutor(libro4.getId(), autorTolstoi.getId());
            libroService.agregarAutor(libro5.getId(), autorAsimov.getId());
            libroService.agregarAutor(libro6.getId(), autorGarcia.getId()); // García Márquez ten dous libros
            System.out.println("Autores asignados correctamente");
            System.out.println();

            // PROBA 1: LISTAR TODOS OS LIBROS
            System.out.println("========== PROBA 1: LISTAR TODOS OS LIBROS ==========");
            List<Libro> todosLibros = libroService.listarTodos();
            todosLibros.forEach(l -> System.out.println(l));
            System.out.println();

            //PROBA 2: BUSCAR LIBROS DESDE UN ANO 
            System.out.println("========== PROBA 2: LIBROS DESDE O ANO 1960 ==========");
            List<Libro> librosDesde1960 = libroService.buscarDesdeAno(1960);
            librosDesde1960.forEach(l -> System.out.println(l.getTitulo() + " (" + l.getAnoPublicacion() + ")"));
            System.out.println();

            //PROBA 3: BUSCAR POR ISBN
            System.out.println("========== PROBA 3: BUSCAR POR ISBN ==========");
            String isbnBuscar = "978-8437604947";
            System.out.println("   Buscando ISBN: " + isbnBuscar);
            Optional<Libro> libroPorIsbn = libroService.buscarPorIsbn(isbnBuscar);
            libroPorIsbn.ifPresentOrElse(
                l -> System.out.println("Encontrado: " + l.getTitulo()),
                () -> System.out.println("Non encontrado")
            );
            System.out.println();

            //PROBA 4: BUSCAR POR NOME DE EDITORIAL
            System.out.println("========== PROBA 4: LIBROS DA EDITORIAL 'penguin' ==========");
            List<Libro> librosPorEditorial = libroService.buscarPorNombreEditorial("penguin");
            librosPorEditorial.forEach(l -> System.out.println(l.getTitulo() + " - " + l.getEditorial().getNombre()));
            System.out.println();

            //PROBA 5: BUSCAR POR NOME DE AUTOR
            System.out.println("========== PROBA 5: LIBROS DO AUTOR 'gabriel' ==========");
            List<Libro> librosPorAutorNome = libroService.buscarPorNombreAutor("gabriel");
            librosPorAutorNome.forEach(l -> System.out.println(l.getTitulo()));
            System.out.println();

            //PROBA 6: BUSCAR POR ID DE CATEGORÍA
            System.out.println("========== PROBA 6: LIBROS DA CATEGORÍA 'Ficción' (id=" + catFiccion.getId() + ") ==========");
            List<Libro> librosPorCategoria = libroService.buscarPorCategoriaId(catFiccion.getId());
            librosPorCategoria.forEach(l -> System.out.println(l.getTitulo() + " [" + l.getCategoria().getNombre() + "]"));
            System.out.println();

            // PROBA 7: BUSCAR POR ID DE AUTOR
            System.out.println("========== PROBA 7: LIBROS DO AUTOR García Márquez (id=" + autorGarcia.getId() + ") ==========");
            List<Libro> librosPorAutorId = libroService.buscarPorAutorId(autorGarcia.getId());
            librosPorAutorId.forEach(l -> System.out.println(l.getTitulo()));
            System.out.println();

            // ===== PROBA 8: AUTORES CON LIBROS CARGADOS (JPQL) =====
            System.out.println("========== PROBA 8: AUTORES CON LIBROS CARGADOS ==========");
            List<Autor> autoresConLibros = autorService.listarTodosConLibros();
            autoresConLibros.forEach(a -> {
                System.out.println(a.getNombre() + " " + a.getApellidos() + " -> " + a.getLibros().size() + " libro(s)");
                a.getLibros().forEach(l -> System.out.println("      - " + l.getTitulo()));
            });
            System.out.println();

            //PROBA 9: BUSCAR AUTORES POR NOME
            System.out.println("========== PROBA 9: AUTORES CO NOME 'george' ==========");
            List<Autor> autoresPorNome = autorService.buscarPorNombre("george");
            autoresPorNome.forEach(a -> System.out.println(a.getNombre() + " " + a.getApellidos()));
            System.out.println();

            //PROBA 10: AUTORES POR RANGO DE DATA DE NACEMENTO
            System.out.println("========== PROBA 10: AUTORES NACIDOS ENTRE 1900 E 1950 ==========");
            Date desde = sdf.parse("1900-01-01");
            Date hasta = sdf.parse("1950-12-31");
            List<Autor> autoresPorFecha = autorService.buscarPorRangoFechaNacimiento(desde, hasta);
            autoresPorFecha.forEach(a -> System.out.println(a.getNombre() + " " + a.getApellidos() + " (" + sdf.format(a.getFechaNacimiento()) + ")"));
            System.out.println();

            //PROBA 11: ELIMINAR CATEGORÍA
            System.out.println("========== PROBA 11: ELIMINAR CATEGORÍA 'Historia'==========");
            System.out.println("   Libros en categoría Historia antes de eliminar:");
            List<Libro> librosHistoria = libroService.buscarPorCategoriaId(catHistoria.getId());
            librosHistoria.forEach(l -> System.out.println("      - " + l.getTitulo()));

            categoriaService.eliminarCategoria(catHistoria.getId());
            System.out.println("Categoría 'Historia' eliminada");

            System.out.println("   Verificando que os libros de Historia foron eliminados automaticamente");
            List<Libro> librosHistoriaDespois = libroService.buscarPorCategoriaId(catHistoria.getId());
            System.out.println("   Libros en categoría Historia despois de borrar: " + librosHistoriaDespois.size() + " (deben ser 0)");
            System.out.println();

            //ESTADO FINAL
            System.out.println("========== ESTADO FINAL: TODOS OS LIBROS EXISTENTES ==========");
            List<Libro> librosFinais = libroService.listarTodos();
            librosFinais.forEach(l -> System.out.println(l.getTitulo() + " [" + l.getCategoria().getNombre() + "]"));
            System.out.println();

            System.out.println("========== FIN DAS PROBAS ==========\n");
        };
    }
}