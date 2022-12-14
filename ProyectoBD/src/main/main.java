package main;

import java.sql.SQLException;
import java.util.Scanner;
import models.Actividad;
import models.dao.DaoManager;

public class main {

	public static void main(String[] args) {
		int op = 0;
		Scanner input = new Scanner(System.in);
		DaoManager daoManager = new DaoManager();
		do {
			if (op==0) {
				menu();
				op = input.nextInt();
			}
			input.nextLine();
			
			try {				
				
				switch (op) {
				case 1: daoManager.getActividadDao().insert(cargarActividad(input));
					break;
				case 2: System.out.println("Ingrese el codigo o el nombre de la materia que quiere eliminar");
					daoManager.getMateriaDao().delete(input.nextLine());		// ver la eliminacion en cascasda	
					break;
				case 3: System.out.println("Ingrese el nombre de la materia"); 
					System.out.println(daoManager.getMateriaDao().getAlumnos(input.nextLine()));
					break;
				case 4: daoManager.closeConnection();
					System.out.println("La ejecucion se finalizo correctamente");
					break;
				default: System.out.println("Ingrese una opcion valida");
						 op = 0;
					break;
				}
				
				
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println("Error sql \n"+e.getMessage());
			}
			
			while ( op > 0 && op < 4 ) {
				System.out.println(" \n Para volver al menu presione 0 y para salir 4");
				op = input.nextInt();
				if (op == 4 ) System.out.println("La ejecucion se finalizo correctamente");
			}
			
			
		} while (op != 4 );
		
	}
	
	private static Actividad cargarActividad(Scanner input) {
		Actividad actividad = new Actividad();

		System.out.println("Ingrese la descripcion de la actividad");
		actividad.setDescipcion(input.next());
		System.out.println("Ingrese el codigo de la materia");
		actividad.setCod_materia(input.nextInt());
		
		return actividad;
	}
	
	private static void menu() {
		
		System.out.println("\n Ingrese una opcion ");
		System.out.println(" 1- Insertar una actividad ");
		System.out.println(" 2- Eliminar una materia ");
		System.out.println(" 3- Listar alumnos de una materia ");
		System.out.println(" 4- Salir \n");
				
	}

}
