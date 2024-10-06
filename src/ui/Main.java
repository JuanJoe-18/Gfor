package ui;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.Controller;
import model.Department;
import model.ProblemTypes;

public class Main {

    private Scanner rd;
    private Controller controller;

    public static void main(String[] args) {
        Main main = new Main();
        main.start();
    }

    public Main() {
        rd = new Scanner(System.in);
        controller = new Controller(new Department[32], null);
        System.out.println("Iniciando sistema...\n\nPrograma de la COP16 (Digite una opción)");
    }

    public void start() {
        while (true) {
            menu();
        }
    }

    private void menu() {
        System.out.println("""
                1. Registrar voluntario
                2. Registrar lugar biodiverso
                3. Registrar ruta
                4. Registrar caminata
                5. Mostrar lugares ordenados por área
                6. Mostrar departamento con más lugares biodiversos
                7. Registrar comunidad
                8. Registrar especie
                9. Mostrar lugares con más especies
                10. Mostrar lugares sin escuelas u hospitales
                11. Cambiar la información de una especie
                12. Salir
                    """);

        int option = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea

        switch (option) {
            case 1:
                addVolunteer();
                break;
            case 2:
                registerBiodiversePlace();
                break;
            case 3:
                addRoute();
                break;
            case 4:
                System.out.println(controller.showBiodiversePlacesOrder());
                break;
            case 5:
                System.out.println(controller.mostBiodiverseDepartment());
                break;
            case 6:
                registerCommunity();
                break;
            case 7:
                addSpecies();
                break;
            case 8:
                showPlacesWithMostSpecies();
                break;
            case 9:
                System.out.println(controller.showPlacesWithoutSchoolOrHospital());
                break;
            case 10:
                modifySpecies();
                break;
            case 0:
                System.out.println("Saliendo del sistema");
                System.exit(0);
            default:
                System.out.println("Opción no válida");
        }
    }

    private void addVolunteer() {
        System.out.print("Ingrese su nombre: ");
        String name = rd.nextLine();
        System.out.print("Ingrese su cédula: ");
        String cedula = rd.nextLine();

        boolean thereIsVolunteer = controller.addVolunteer(name, cedula);
        controller.rectifyVolunteer(thereIsVolunteer);
    }

    public void registerBiodiversePlace() {
        System.out.println("Registrar lugar biodiverso");

        System.out.print("Ingrese el departamento: ");
        String department = rd.nextLine();

        System.out.print("Ingrese el nombre del lugar: ");
        String placeName = rd.nextLine();

        System.out.print("Ingrese el tamaño del lugar (en hectáreas): ");
        double placeSize = rd.nextDouble();
        rd.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese la URL de la foto del lugar: ");
        String placePhoto = rd.nextLine();

        Date openingDate = readDate("Ingrese la fecha de apertura (dd/MM/yyyy): ");

        System.out.print("Ingrese los recursos necesarios: ");
        Double resourcesNeeded = rd.nextDouble();
        rd.nextLine(); // Consumir el salto de línea

        if (controller.addBiodiversePlaceInDepartment(placeName, department, placeSize, placePhoto, openingDate, resourcesNeeded)) {
            System.out.println("Lugar registrado: " + placeName + ", Tamaño: " + placeSize + " hectáreas, Foto: "
                    + placePhoto + ", Fecha de apertura: " + openingDate);
        } else {
            System.out.println("No se pudo registrar el lugar.");
        }
    }

    private void addRoute() {
        System.out.println("Registro de ruta.\n");
        boolean isValidName = false;
        do {
            System.out.print("Ingrese el nombre de la ruta: ");
            String name = rd.nextLine();

            switch (name) {
                case "Farallones":
                    isValidName = true;
                    System.out.println("¡Excelente! La Ruta Farallones tiene como punto de  encuentro: Calle 16 - Universidad del Valle  iniciando a las  6:40 am  y termina a las  4:00 pm .");
                    break;
                case "Pance":
                    isValidName = true;
                    System.out.println("¡Excelente! La Ruta  Pance  tiene como punto de  encuentro:  Cra.122 Universidad Icesi  iniciando a las  7:00 am  y termina a las  5:00 pm .");
                    break;
                case "Navarro":
                    isValidName = true;
                    System.out.println("¡Excelente! La Ruta  Navarro  tiene como punto de  encuentro: Cra.41e-3 Parque Manantial  iniciando a las  10:00 am  y termina a las  6:00 pm .");
                    break;
                default:
                    System.out.println("El nombre de la ruta no existe, por favor intente de nuevo con uno de los siguientes: Farallones, Pance o Navarro");
            }
        } while (isValidName == false);

        System.out.print("Ingrese la cantidad de guías: ");
        int guidesQtty = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese la cantidad de participantes: ");
        int participantsQtty = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese la temperatura: ");
        double temperature = rd.nextDouble();
        rd.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese la humedad: ");
        double humidity = rd.nextDouble();
        rd.nextLine();

        controller.weatherForecast(temperature, humidity);

        controller.calculateBuses(participantsQtty, guidesQtty);

    }

    private void registerCommunity() {
        System.out.println("Registrar comunidad");

        System.out.println(controller.showDepartments());
        System.out.print("Ingrese el índice / id del departamento: ");
        int departmentIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea

        System.out.println(controller.showPlacesInDepartment(departmentIndex));
        System.out.print("Ingrese el índice / id del lugar: ");
        int placeIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el nombre de la comunidad: ");
        String communityName = rd.nextLine();

        System.out.println("Tipos de comunidad disponibles:");
        System.out.println(controller.showCommunityTypes());
        System.out.print("Ingrese el tipo de comunidad: ");
        String communityType = rd.nextLine();

        System.out.print("Ingrese el tamaño de la comunidad: ");
        int communitySize = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea

        System.out.println("Tipos de problemas disponibles:");
        System.out.println(controller.showProblemTypes());
        System.out.print("Ingrese hasta 4 problemas de la comunidad (separados por comas y un espacio): ");
        String problemsInput = rd.nextLine();
        String[] problemsStr = problemsInput.split(", ");
        if (problemsStr.length > 4) {
            System.out.println("Solo se permiten hasta 4 problemas.");
            return;
        }

        ProblemTypes[] problems = new ProblemTypes[problemsStr.length];
        for (int i = 0; i < problemsStr.length; i++) {
            problems[i] = ProblemTypes.valueOf(problemsStr[i].toUpperCase());
        }

        System.out.print("Ingrese el nombre del representante de la comunidad: ");
        String nameRepresentative = rd.nextLine();

        System.out.print("Ingrese el teléfono del representante: ");
        String phoneRepresentative = rd.nextLine();

        boolean success = controller.addCommunity(departmentIndex, placeIndex, communityName, communityType,
                communitySize, problems, nameRepresentative, phoneRepresentative);

        if (success) {
            System.out.println("Comunidad registrada exitosamente.");
        } else {
            System.out.println("Error al registrar la comunidad.");
        }
    }

    private void addSpecies() {
        System.out.println("Registrar especie");

        System.out.println(controller.showDepartments());
        System.out.print("Ingrese el índice del departamento: ");
        int departmentIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea

        System.out.println(controller.showPlacesInDepartment(departmentIndex));
        System.out.print("Ingrese el índice del lugar: ");
        int placeIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea

        System.out.print("Ingrese el nombre de la especie: ");
        String animalName = rd.nextLine();

        boolean success = controller.addSpeciesToPlace(departmentIndex, placeIndex, newAnimal);

        if (success) {
            System.out.println("Especie registrada correctamente.");
        } else {
            System.out.println("Error al registrar la especie.");
        }
    }

    private void showPlacesWithMostSpecies() {
        System.out.println("Mostrar lugares con más especies");
        // Aquí deberías agregar la lógica para obtener y mostrar los lugares con más especies
        // Puedes usar los métodos del controlador para obtener la información necesaria
    }

    private void modifySpecies() {
        System.out.println("Modificar información de una especie.");
        System.out.print("Ingrese el índice del departamento: ");
        int departmentIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el índice del lugar: ");
        int placeIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el índice de la especie: ");
        int speciesIndex = rd.nextInt();
        rd.nextLine(); // Consumir el salto de línea
        System.out.print("Ingrese el nuevo nombre de la especie: ");
        String newSpeciesName = rd.nextLine();

        boolean success = controller.modifySpeciesInPlace(departmentIndex, placeIndex, speciesIndex, updatedAnimal);

        if (success) {
            System.out.println("Especie modificada correctamente.");
        } else {
            System.out.println("Error al modificar la especie.");
        }
    }

    private Date readDate(String message) {
        Date date = null;
        boolean validDate = false;
        while (!validDate) {
            try {
                System.out.print(message);
                String dateInput = rd.nextLine();
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                date = dateFormat.parse(dateInput);
                validDate = true;
            } catch (ParseException e) {
                System.out.println("Formato de fecha incorrecto. Utilice dd/MM/yyyy.");
            }
        }
        return date;
    }
}
