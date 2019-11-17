public interface TADcjtEtiquetes {

    /**
     * Afegim les dades d'una etiqueta. Aquesta pot existir previament o no.
     * @param nomEtiqueta
     * @param dia
     * @param mes
     * @param any
     * @param nomUsuari
     * @param missatge
     */
    void afegirDadesEtiqueta(String nomEtiqueta, int dia, int mes, int any, String nomUsuari, String missatge);

    /**
     * Esborrem una etiqueta de la nostra llista.
     * @param nomEtiqueta nom de la etiqueta a esborrar
     */
    Etiqueta esborrarEtiqueta(String nomEtiqueta);

    /**
     * Esborrem totes les piulades fetes a una data concreta.
     * @param dia
     * @param mes
     * @param any
     */
    void esborrarDadesPiuladesAData(int dia, int mes, int any);

    /**
     * Retornem una llista de noms d'usuaris que han fet servir una certa etiqueta.
     * @param nomEtiqueta etiqueta de la que busquem usuaris
     * @return Llista d'usuaris que compeixen la condició
     */
    TADLlistaGenerica<String> usuarisPerEtiqueta(String nomEtiqueta);

    /**
     * Retornem una llista de piulades fetes a una certa data amb una certa etiqueta.
     * @param dia dia
     * @param mes
     * @param any
     * @param nomEtiqueta
     * @return Llista de piulades que compleixen la condició
     */
    TADLlistaGenerica<Piulada> piuladesPerDataPerEtiqueta(int dia, int mes, int any, String nomEtiqueta);

    /**
     * Retornem l'etiqueta amb més usos
     * @return
     */
    String etiquetaMesUtilitzada();

    /**
     * Retorna una llista amb les etiquetes utilitzades per un usuari
     * @param usuari
     * @return
     */
    TADLlistaGenerica<String> etiquetesdUsuari(String usuari);

    /**
     * Número de piulades que ha fet un usuari.
     * @param nomUsuari
     * @return
     */
    int numPiuladesdUsuari(String nomUsuari);





}
