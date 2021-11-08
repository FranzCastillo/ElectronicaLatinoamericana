import Productos.*;
import java.util.ArrayList;
import java.io.IOException;
import java.io.FileNotFoundException;     
import java.io.FileWriter; 

public class ElectronicaLatinoamericana {
    private ArrayList<Producto> productos;
    private Carrito carrito;
    private Vista v;
    private String[] lectura;
    private File archivo = new File("Productos.txt");

    public ElectronicaLatinoamericana(){
        productos = new ArrayList<>();
        carrito = new Carrito();
        v = new Vista();
    }
    public void ejecutar(){
        // Telfono;iPhone;Apple

        productos.add(new Productos.Dispositivos.Smartphone("iPhone", "Apple", 1200, "11", "11/6/2021", "ARMSDMJD"));
        v.inicio();
        while(true){
            int opcion = v.mostarMenuPrincipal();
            switch(opcion){
                case 1: //CREAR UN PRODUCTO
                    /*Camara c = new Camara();
                    productos.add(c);
                    guardarProducto(c);*/
                break;
                case 2: //VER PRODUCTO
                    verProducto();
                break;
                case 3: //MOSTAR CARRITO DE COMPRA
                break;
                case 4: //SALIR
                    v.fin();
                    System.exit(1);
                break;
                default:
                    v.mostrarOpcionInvalida();
                break;
            }
        }
    }

    private void leerArchivo()
    {
        //validación
        try 
        {    
            Scanner lector = new Scanner(archivo); //leer archivo
            while (lector.hasNextLine()) //analizar archivo linea por linea
            {
                lectura = lector.nextLine().split(";");
                //convertir datos del archivo a objetos de clase Dispositivo
                if (lectura[0].equals("Tablet"))
                {
                    productos.add(new Productos.Dispositivos.Tablet());
                }
            }
            lector.close(); //cerrar archivo
        } 
        catch (FileNotFoundException e) 
        {
            v.mostrarErrorArchivo();
        }
    }

    private void verProducto(){
        boolean regresarMenuPrincipal = false;
        while(!regresarMenuPrincipal){
            Producto productoActual = v.seleccionarProducto(productos); //DEVUELVE NULL SI EL USUARIO DESEA SALIR
            if(productoActual == null){
                regresarMenuPrincipal = true; //TERMINARIA EL CICLO DE LA FUNCION AL NO CUMPLIR CON EL WHILE
            }else{
                boolean regresarSeleccionDeProducto = false;
                while(!regresarSeleccionDeProducto){
                    int opcionProducto = v.mostrarMenuVerProducto();
                    switch(opcionProducto){
                        case 1: // PROBAR
                            probarProducto(productoActual);
                        break;
                        case 2: // AGREGAR AL CARRITO
                            carrito.agregarAlCarrito(productoActual);
                            v.mostrarAgregadoAlCarrito(productoActual);
                        break;
                        case 3: // REGRESAR A SELECCIONAR PRODUCTO
                            regresarSeleccionDeProducto = true;
                        break;
                        default:
                            v.mostrarOpcionInvalida();
                        break;
                    }
                }
            }
        }
    }
    private void probarProducto(Producto producto){
        boolean regresarSeleccionDeProducto = false;
        while(!regresarSeleccionDeProducto){
            int funcion = v.mostrarMenuProbarProducto(producto);
            switch(funcion){
                case 1: // Videojuego
                    if(producto.ejecutaVidejouegos()){
                        v.mostrarPrueba(producto.probar("ejecutarVideojuego", v.pedirJuego()));
                        
                    }else{
                        v.mostrarProductoNoPuedeHacerFuncion();
                    }
                break;
                case 2: // Llamar
                    if(producto.haceLlamadas()){
                        v.mostrarPrueba(producto.probar("llamar", v.pedirNumero()));
                    }else{
                        v.mostrarProductoNoPuedeHacerFuncion();
                    }
                break;
                case 3: // Internet
                    if(producto.navegaInternet()){
                        v.mostrarPrueba(producto.probar("navegarInternet", v.pedirLink()));
                    }else{
                        v.mostrarProductoNoPuedeHacerFuncion();
                    }                
                break;
                case 4: // Portable
                    if(producto.esPortatil()){
                        v.mostrarPrueba(producto.probar("portabilidad", ""));
                    }else{
                        v.mostrarProductoNoPuedeHacerFuncion();
                    }                
                break;
                case 5: // Video
                    if(producto.reproduceVideos()){
                        v.mostrarPrueba(producto.probar("reproducirVideo", ""));
                    }else{
                        v.mostrarProductoNoPuedeHacerFuncion();
                    }                
                break;
                case 6: // Foto
                    if(producto.tomaFotos()){
                        v.mostrarPrueba(producto.probar("tomarFoto", ""));
                    }else{
                        v.mostrarProductoNoPuedeHacerFuncion();
                    }                
                break;
                case 7: // Salir
                    regresarSeleccionDeProducto = true;
                break;
                default:
                    v.mostrarOpcionInvalida();
                break;
            }
        }
    }
    private void guardarProducto(Producto producto){ //GUARDAR EN EL ARCHIVO

    }
}
