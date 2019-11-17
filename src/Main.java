import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.InputMismatchException;


public class Main {
    static Scanner teclat = new Scanner(System.in);
    /**
     * Funcio principal del programa
     * @param args
     */
    public static void main(String[] args) {

        TADLlistaGenerica<Etiqueta> llistaInterna = menu(); //preguntem al usuari quina estructura de llistes vol i la inicialitzem
        TADcjtEtiquetes hashtagAnalyzer = new Etiquetes(llistaInterna);
        plenarEstructures(hashtagAnalyzer);
        while (true) //bucle infinit del menu
        {
            consultes(hashtagAnalyzer);
        }
    }
    /**
     * Metode per a escollir la estructura a implementar.
     * @return TAD - estructura creada.
     */
    public static TADLlistaGenerica<Etiqueta> menu(){ //mostra el menu i inicialitza el TAD
        int opt=0;
        TADLlistaGenerica<Etiqueta> tad = null;
        while (tad == null) //iterarem mentre que el usuari no indiqui l'estructura
        {
            System.out.println("Quina versio vols utilitzar?");
            System.out.println("1.- Llista Dinámica");
            System.out.println("2.- Llista Estàtica");
            try
            {
                opt = teclat.nextInt();
                teclat.nextLine();
                switch(opt) {
                    case 1:
                        tad = new LlistaDinamica<Etiqueta>();
                        break;
                    case 2:
                        System.out.println("Introdueix longitud per a la llista Estàtica");
                        try
                        {
                            opt = teclat.nextInt();
                            teclat.nextLine();
                        }
                        catch (InputMismatchException e)
                        {
                            System.out.println("\nERROR:Has introduit una opcio incorrecta, torna-ho a intentar. \n");
                            break;  // Sortim del case intern per a repetir l'extern
                        }
                        tad = new LlistaEstatica<Etiqueta>(opt);
                        break;
                    default: System.out.println("\nAquesta opcio no esta a la llista. \n");
                        break;	//Funciona com una excepcio per a un valor numeric no acceptat
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("\nExceptions.InputMismatchException: ERROR:Has introduit una opcio incorrecta, torna-ho a intentar. \n");
            }
        }
        return tad;
    }
    /**
     * Metode per a consultar les dades de l'estructura
     */
    public static void consultes(TADcjtEtiquetes tad){ //mostra les consultes
        int opt = 0, dia, mes, any;
        long ti = 0, tf = 0; // temps per a mesurar l'eficiencia de l'algorisme
        String nomUsuari = "", missatge = "", etiqueta = ""; //entrada generica de teclat.
        while (true)  // Sortirem del programa amb la opcio del menu
        {
            System.out.println("Quina operacio vols fer?");
            System.out.println("0.- Mostrar Estructura de Dades");
            System.out.println("1.- Afegir dades d'una etiqueta.");
            System.out.println("2.- Esborrar dades d'una etiqueta");
            System.out.println("3.- Esborrar dades de piulades fetes en una data concreta");
            System.out.println("4.- Obtenir usuaris que han fet servir una determinada etiqueta");
            System.out.println("5.- Obtenir piulades que s'han fet en una data i amb una etiqueta concreta.");
            System.out.println("6.- Obtenir etiqueta amb més piulades.");
            System.out.println("7.- Obtenir etiquetes que ha fet servir un usuari.");
            System.out.println("8.- Obtenir nombre de piulades que ha fet un determinat usuari.");
            System.out.println("9.- Sortir.");

            try
            {
                opt = teclat.nextInt();
                teclat.nextLine();
                switch (opt) {
                    case 0:
                        ti = System.nanoTime();
                        System.out.println(tad.toString());
                        tf = System.nanoTime();
                        break;
                    case 1:
                        System.out.print("Introdueix el nom de l'etiqueta (precedida de #): ");
                        etiqueta = teclat.nextLine();
                        System.out.print("Introdueix dia: ");
                        dia = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix mes: ");
                        mes = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix any: ");
                        any = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix usuari (precedit de @): ");
                        nomUsuari = teclat.nextLine();
                        System.out.print("Introdueix cos de la Piulada: ");
                        missatge = teclat.nextLine();

                        ti = System.nanoTime();	// Inicialització
                        tad.afegirDadesEtiqueta(etiqueta, dia, mes, any, nomUsuari, missatge);
                        tf = System.nanoTime();	// Parem cronometre
                        break;
                    case 2:
                        System.out.print("Indica l'etiqueta que vols borrar: ");
                        etiqueta = teclat.nextLine();

                        ti = System.nanoTime();	// Inicialització
                        tad.esborrarEtiqueta(etiqueta);
                        tf = System.nanoTime();	// Parem cronometre
                        break;
                    case 3:
                        System.out.print("Introdueix dia: ");
                        dia = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix mes: ");
                        mes = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix any: ");
                        any = Integer.parseInt(teclat.nextLine());

                        ti = System.nanoTime();  // Inicialització
                        tad.esborrarDadesPiuladesAData(dia, mes, any);
                        tf = System.nanoTime();	 // Parem cronometre
                        break;
                    case 4:
                        System.out.print("Introdueix el nom de l'etiqueta (precedida de #): ");
                        etiqueta = teclat.nextLine();

                        ti = System.nanoTime();  // Inicialització
                        System.out.println(tad.usuarisPerEtiqueta(etiqueta));
                        tf = System.nanoTime();	 // Parem cronometre
                        break;
                    case 5:
                        System.out.print("Introdueix el nom de l'etiqueta (precedida de #): ");
                        etiqueta = teclat.nextLine();
                        System.out.print("Introdueix dia: ");
                        dia = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix mes: ");
                        mes = Integer.parseInt(teclat.nextLine());
                        System.out.print("Introdueix any: ");
                        any = Integer.parseInt(teclat.nextLine());

                        ti = System.nanoTime();	// Inicialització
                        System.out.println(tad.piuladesPerDataPerEtiqueta(dia, mes, any, etiqueta));
                        tf = System.nanoTime();	// Parem cronometre
                        break;
                    case 6:
                        ti = System.nanoTime();	// Inicialització
                        System.out.println(tad.etiquetaMesUtilitzada());
                        tf = System.nanoTime();	// Parem cronometre
                        break;
                    case 7:
                        System.out.print("Introdueix nom dusuari (precedit de @): ");
                        nomUsuari = teclat.nextLine();

                        ti = System.nanoTime();	// Inicialització
                        System.out.println(tad.etiquetesdUsuari(nomUsuari));
                        tf = System.nanoTime();	// Parem cronometre
                        break;
                    case 8:
                        System.out.print("Introdueix nom dusuari (precedit de @): ");
                        nomUsuari = teclat.nextLine();

                        ti = System.nanoTime();	// Inicialització
                        System.out.println(tad.numPiuladesdUsuari(nomUsuari));
                        tf = System.nanoTime();	// Parem cronometre
                        break;
                    case 9: //apagar la consola
                        System.exit(0); break; //termina l'aplicacio que s'esta executant al moment
                    default: System.out.println("\nAquesta opcio no esta a la llista... \n");
                        break;	//Funciona com una excepcio per a un valor numeric no acceptat
                }
                System.out.println("\nHa tardat "+ (tf-ti)/Math.pow(10, 9)+ " segons");

                tf = System.nanoTime();
            }
            catch (InputMismatchException e)
            {
                System.out.println("Exceptions.InputMismatchException: ERROR:Has introduit una opcio incorrecta, torna-ho a intentar \n");
                teclat.nextLine();
            }
        }
    }

    public static void plenarEstructures(TADcjtEtiquetes hashtagAnalyzer) {
        Scanner f = null;
        try {
            f = new Scanner(new File("Twitter.csv"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Fitxer no trobat");
            System.exit(1);

        }
        while (f.hasNext())
        {
            String piulada = f.nextLine();  // Obtenim següent trosset
            String[] splittedLine = piulada.split(" ");  // Agafem paraules separades per espais
            String[] splittedDate = splittedLine[0].split("/");  // Trenquem la data i obtenim info
            int any = Integer.parseInt(splittedDate[0]), mes = Integer.parseInt(splittedDate[1]), dia = Integer.parseInt(splittedDate[2]);  // obtenim data del tweet
            String nomUsuari = splittedLine[2];  // Obtenim usuari autor

            // Creem una de les nostres llistes per guardar les etiquetes
            TADLlistaGenerica<String> etiquetes = new LlistaDinamica<>();
            for (String s : splittedLine) {
                if (s.startsWith("#")) {
                    try {
                        etiquetes.afegir(s);
                    } catch (LlistaPlena llistaPlena) {
                        System.out.println("Llista plena");
                    }
                }
            }
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 3; i < splittedLine.length; i++)
            {
                stringBuilder.append(splittedLine[i]);
                stringBuilder.append(" ");
            }
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
            piulada = stringBuilder.toString();  // retallem el tweet correctament

            Iterator<String> iteradorEtiquetes = etiquetes.iterator();
            while (iteradorEtiquetes.hasNext())
            {
                    hashtagAnalyzer.afegirDadesEtiqueta(iteradorEtiquetes.next().getObj(), dia, mes, any, nomUsuari, piulada);
            }
        }
        f.close();
    }
}
