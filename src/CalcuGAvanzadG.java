import java.util.ArrayList;
import java.util.Scanner;

// Interfaz de operaciones
interface Operacion {
    double calcularArea();
    double calcularPerimetro();
}

// Clase base Figura
abstract class Figura implements Operacion {
    // Método común para leer valores
    public static double leerDouble(Scanner scanner, String mensaje) {
        double valor;
        while (true) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextDouble();
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida, inténtelo de nuevo.");
                scanner.next(); // Limpiar entrada
            }
        }
        return valor;
    }
}

// Clase Círculo
class Circulo extends Figura {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double calcularArea() {
        return Math.PI * Math.pow(radio, 2);
    }

    @Override
    public double calcularPerimetro() {
        return 2 * Math.PI * radio;
    }
}

// Clase Cuadrado
class Cuadrado extends Figura {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return Math.pow(lado, 2);
    }

    @Override
    public double calcularPerimetro() {
        return 4 * lado;
    }
}

// Clase Triángulo
class Triangulo extends Figura {
    private double base, altura, lado1, lado2;

    public Triangulo(double base, double altura, double lado1, double lado2) {
        this.base = base;
        this.altura = altura;
        this.lado1 = lado1;
        this.lado2 = lado2;
    }

    @Override
    public double calcularArea() {
        return (base * altura) / 2;
    }

    @Override
    public double calcularPerimetro() {
        return base + lado1 + lado2;
    }
}

// Clase Rectángulo
class Rectangulo extends Figura {
    private double largo, ancho;

    public Rectangulo(double largo, double ancho) {
        this.largo = largo;
        this.ancho = ancho;
    }

    @Override
    public double calcularArea() {
        return largo * ancho;
    }

    @Override
    public double calcularPerimetro() {
        return 2 * (largo + ancho);
    }
}

// Clase Pentágono
class Pentagono extends Figura {
    private double lado;

    public Pentagono(double lado) {
        this.lado = lado;
    }

    @Override
    public double calcularArea() {
        return (5 * Math.pow(lado, 2)) / (4 * Math.tan(Math.PI / 5));
    }

    @Override
    public double calcularPerimetro() {
        return 5 * lado;
    }
}

// Clase Potencia
class Potencia {
    private double base;
    private int exponente;

    public Potencia(double base, int exponente) {
        this.base = base;
        this.exponente = exponente;
    }

    public double calcularPotencia() {
        if (exponente == 0) {
            return 1;
        }
        return base * calcularPotencia(base, exponente - 1);
    }

    private double calcularPotencia(double base, int exponente) {
        if (exponente == 0) {
            return 1;
        }
        return base * calcularPotencia(base, exponente - 1);
    }
}

// Clase Calculadora
public class CalcuGAvanzadG {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> resultados = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            int figura = 0;
            do {
                mostrarMenuFiguras();
                figura = leerEntero(scanner, "Seleccione una figura (1-6): ");
                if (figura < 1 || figura > 6) {
                    System.out.println("Error: Debe seleccionar una figura entre 1 y 6.");
                }
            } while (figura < 1 || figura > 6);

            int operacion = 0;
            do {
                mostrarMenuOperaciones();
                operacion = leerEntero(scanner, "Seleccione una operación (1-2): ");
                if (operacion < 1 || operacion > 2) {
                    System.out.println("Error: Debe seleccionar una operación válida (1 o 2).");
                }
            } while (operacion < 1 || operacion > 2);

            Figura figuraSeleccionada = crearFigura(figura, scanner);

            if (figuraSeleccionada != null) {
                double resultado = (operacion == 1) ? figuraSeleccionada.calcularArea() : figuraSeleccionada.calcularPerimetro();
                resultados.add(resultado);
                System.out.println("El resultado de la operación es: " + resultado);
            } else if (figura == 6) { // Si se seleccionó potencia, calcular la potencia
                double basePotencia = Figura.leerDouble(scanner, "Ingrese la base: ");
                int exponente = leerEntero(scanner, "Ingrese el exponente: ");
                Potencia potencia = new Potencia(basePotencia, exponente);
                double resultadoPotencia = potencia.calcularPotencia();
                resultados.add(resultadoPotencia);
                System.out.println("El resultado de la potencia es: " + resultadoPotencia);
            }

            continuar = validarContinuar(scanner);
        }

        System.out.println("\nResultados almacenados:");
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("Operación " + (i + 1) + ": " + resultados.get(i));
        }

        scanner.close();
    }

    public static Figura crearFigura(int figura, Scanner scanner) {
        switch (figura) {
            case 1:
                double radio = Figura.leerDouble(scanner, "Ingrese el radio del círculo: ");
                return new Circulo(radio);
            case 2:
                double ladoCuadrado = Figura.leerDouble(scanner, "Ingrese el lado del cuadrado: ");
                return new Cuadrado(ladoCuadrado);
            case 3:
                double base = Figura.leerDouble(scanner, "Ingrese la base del triángulo: ");
                double altura = Figura.leerDouble(scanner, "Ingrese la altura del triángulo: ");
                double lado1 = Figura.leerDouble(scanner, "Ingrese el primer lado del triángulo: ");
                double lado2 = Figura.leerDouble(scanner, "Ingrese el segundo lado del triángulo: ");
                return new Triangulo(base, altura, lado1, lado2);
            case 4:
                double largo = Figura.leerDouble(scanner, "Ingrese el largo del rectángulo: ");
                double ancho = Figura.leerDouble(scanner, "Ingrese el ancho del rectángulo: ");
                return new Rectangulo(largo, ancho);
            case 5:
                double ladoPentagono = Figura.leerDouble(scanner, "Ingrese el lado del pentágono: ");
                return new Pentagono(ladoPentagono);
            default:
                System.out.println("Figura no válida.");
                return null;
        }
    }

    public static void mostrarMenuFiguras() {
        System.out.println("\nSeleccione la figura geométrica:");
        System.out.println("1. Círculo");
        System.out.println("2. Cuadrado");
        System.out.println("3. Triángulo");
        System.out.println("4. Rectángulo");
        System.out.println("5. Pentágono");
        System.out.println("6. Potencia");
    }

    public static void mostrarMenuOperaciones() {
        System.out.println("\nSeleccione la operación:");
        System.out.println("1. Área");
        System.out.println("2. Perímetro");
    }

    public static int leerEntero(Scanner scanner, String mensaje) {
        int valor;
        while (true) {
            try {
                System.out.print(mensaje);
                valor = scanner.nextInt();
                break;
            } catch (Exception e) {
                System.out.println("Entrada inválida, inténtelo de nuevo.");
                scanner.next(); // Limpiar entrada
            }
        }
        return valor;
    }

    public static boolean validarContinuar(Scanner scanner) {
        while (true) {
            System.out.print("¿Desea realizar otra operación? (si/no): ");
            String respuesta = scanner.next();
            if (respuesta.equalsIgnoreCase("si")) {
                return true;
            } else if (respuesta.equalsIgnoreCase("no")) {
                return false;
            } else {
                System.out.println("Respuesta inválida, inténtelo de nuevo.");
            }
        }
    }
}
