public class Etiquetes implements  TADcjtEtiquetes{

    TADLlistaGenerica<Etiqueta> llistaEtiquetes;
    /**
     * Guardem quina implementació fem per a definir les subllistes internes y que siguin del mateix tipus
     */
    public final int DYNAMIC = 1;
    public final int STATIC = 0;
    public int implementation;

    public static int dim = 1000;

    /**
     * Constructor genèric. Rep una llista genèrica d'etiquetes (rebem una implementació que pot canviarse en temps d'execució)
     * @param llistaEtiquetes
     */
    public Etiquetes(TADLlistaGenerica<Etiqueta> llistaEtiquetes) {
        this.llistaEtiquetes = llistaEtiquetes;
        if (llistaEtiquetes instanceof LlistaDinamica)
        {
            this.implementation = DYNAMIC;
        }
        else
        {
            this.implementation = STATIC;
        }
    }

    @Override
    public void afegirDadesEtiqueta(String nomEtiqueta, int dia, int mes, int any, String nomUsuari, String missatge) {
        // Mirem si hi ha algun objecte Etiqueta ja existent amb aquest nom
        Obj<Etiqueta> etiquetaConsultada = this.llistaEtiquetes.consultar(new Etiqueta(nomEtiqueta, null));
        if (etiquetaConsultada == null)  // Si consultar retorna null es tracta d'una nova piulada
        {
            // Definim una llista generica
            TADLlistaGenerica<Piulada> llistainterna;

            // La inicialitzem segons la implementació que tinguem guardada (definida en el moment de cridar al constructor d'aquesta classe)
            if (this.implementation == DYNAMIC) llistainterna = new LlistaDinamica<Piulada>();
            else llistainterna = new LlistaEstatica<Piulada>(Etiquetes.dim);

            // Afegim la nova etiqueta amb la seva llista de piulades, que contindra nomes una piulada ja que es nova
            try {
                llistainterna.afegir(new Piulada(missatge, new Data(dia, mes, any), nomUsuari));
            } catch (LlistaPlena llistaPlena) {
                llistaPlena.printStackTrace();
                System.out.println("Llista plena");
            }
            try {
                this.llistaEtiquetes.afegir(new Etiqueta(nomEtiqueta, llistainterna));
            } catch (LlistaPlena llistaPlena) {
                llistaPlena.printStackTrace();
                System.out.println("Llista Plena");
            }
        }
        else  // no retorna null per tant ja hi ha una etiqueta amb aquest nom
        {
            // Treiem l'etiqueta del Wrapper Obj i afegim la piulada en aquesta etiqueta
            try {
                etiquetaConsultada.getObj().getPiulades().afegir(new Piulada(missatge, new Data(dia, mes, any), nomUsuari));
            } catch (LlistaPlena llistaPlena) {
                llistaPlena.printStackTrace();
                System.out.println("Llista Plena");
            }
        }
    }

    @Override
    public Etiqueta esborrarEtiqueta(String nomEtiqueta) {
        try {
            return this.llistaEtiquetes.esborrar(new Etiqueta(nomEtiqueta, null));
        } catch (LlistaBuida llistaBuida) {
            System.out.println("Llista Buida!");
            llistaBuida.printStackTrace();
        }
        return null;
    }

    @Override
    public void esborrarDadesPiuladesAData(int dia, int mes, int any) {
        Iterator<Etiqueta> iteradorEtiquetes = this.llistaEtiquetes.iterator();
        while (iteradorEtiquetes.hasNext())
        {
            Etiqueta etiquetaActual = iteradorEtiquetes.next().getObj();
            Iterator<Piulada> iteradorPiulades = etiquetaActual.getPiulades().iterator();

            while (iteradorPiulades.hasNext())
            {
                Piulada piuladaActual = iteradorPiulades.next().getObj();
                if (piuladaActual.getData().compareTo(dia, mes, any) == 0)
                {
                    try {
                        etiquetaActual.getPiulades().esborrar(piuladaActual);
                        if (etiquetaActual.getPiulades().getNumElem() == 0)  // No ens quedarem pas amb etiquetes sense piulades
                        {
                            this.llistaEtiquetes.esborrar(etiquetaActual);
                        }
                    } catch (LlistaBuida llistaBuida) {
                        llistaBuida.printStackTrace();
                    }
                }
            }
        }

    }

    @Override
    public TADLlistaGenerica<String> usuarisPerEtiqueta(String nomEtiqueta){
        // Definim una llista generica
        TADLlistaGenerica<String> llistainterna;

        // La inicialitzem segons la implementació que tinguem guardada (definida en el moment de cridar al constructor d'aquesta classe)
        if (this.implementation == DYNAMIC) llistainterna = new LlistaDinamica<String>();
        else llistainterna = new LlistaEstatica<String>(Etiquetes.dim);

        Iterator<Etiqueta> iteradorEtiquetes = this.llistaEtiquetes.iterator();

        while (iteradorEtiquetes.hasNext()) {
            Etiqueta etiquetaActual = iteradorEtiquetes.next().getObj();

            // entrem quan trobem la etiqueta
            if (etiquetaActual.getEtiqueta().equals(nomEtiqueta)) {
                Iterator<Piulada> iteradorPiulades = etiquetaActual.getPiulades().iterator();

                // Iterem sobre les piulades fetes a l'etiqueta requerida
                while (iteradorPiulades.hasNext()) {
                    // Guardem el noms dels usuaris en una llista
                    try {
                        llistainterna.afegir(iteradorPiulades.next().getObj().getCodiUsuari());
                    } catch (LlistaPlena llistaPlena) {
                        llistaPlena.printStackTrace();
                        System.out.println("Llista Plena");
                    }
                }
            }
        }
        return llistainterna;
    }

    @Override
    public TADLlistaGenerica<Piulada> piuladesPerDataPerEtiqueta(int dia, int mes, int any, String nomEtiqueta){
        // Definim una llista generica
        TADLlistaGenerica<Piulada> llistainterna;

        // La inicialitzem segons la implementació que tinguem guardada (definida en el moment de cridar al constructor d'aquesta classe)
        if (this.implementation == DYNAMIC) llistainterna = new LlistaDinamica<Piulada>();
        else llistainterna = new LlistaEstatica<Piulada>(Etiquetes.dim);

        Iterator<Etiqueta> iteradorEtiquetes = this.llistaEtiquetes.iterator();

        while (iteradorEtiquetes.hasNext()) {
            Etiqueta etiquetaActual = iteradorEtiquetes.next().getObj();

            // entrem quan trobem la etiqueta
            if (etiquetaActual.getEtiqueta().compareTo(nomEtiqueta) == 0) {
                Iterator<Piulada> iteradorPiulades = etiquetaActual.getPiulades().iterator();

                // Iterem sobre les piulades fetes a l'etiqueta requerida
                while (iteradorPiulades.hasNext()) {
                    Piulada piuladaActual = iteradorPiulades.next().getObj();
                    if (piuladaActual.getData().compareTo(dia, mes, any) == 0)
                    {
                        try {
                            llistainterna.afegir(piuladaActual);
                        } catch (LlistaPlena llistaPlena) {
                            System.out.println("Llista plena!");
                            llistaPlena.printStackTrace();
                        }
                    }
                }
            }
        }
        return llistainterna;
    }

    @Override
    public String etiquetaMesUtilitzada() {
        Iterator<Etiqueta> iteradorEtiquetes = this.llistaEtiquetes.iterator();
        int max = Integer.MIN_VALUE;
        String etiquetaMesUtilitzada = "";

        while (iteradorEtiquetes.hasNext()) {
            Etiqueta etiquetaActual = iteradorEtiquetes.next().getObj();

            // entrem quan trobem mes Piulades penjant d'una etiqueta que el counter actual
            if (etiquetaActual.getPiulades().getNumElem() > max)
            {
                max = etiquetaActual.getPiulades().getNumElem();
                etiquetaMesUtilitzada = etiquetaActual.getEtiqueta();
            }
        }
        return etiquetaMesUtilitzada;
    }

    @Override
    public TADLlistaGenerica<String> etiquetesdUsuari(String usuari) {
        // Definim una llista generica
        TADLlistaGenerica<String> llistainterna;

        // La inicialitzem segons la implementació que tinguem guardada (definida en el moment de cridar al constructor d'aquesta classe)
        if (this.implementation == DYNAMIC) llistainterna = new LlistaDinamica<String>();
        else llistainterna = new LlistaEstatica<String>(Etiquetes.dim);

        Iterator<Etiqueta> iteradorEtiquetes = this.llistaEtiquetes.iterator();

        while (iteradorEtiquetes.hasNext()) {
            Etiqueta etiquetaActual = iteradorEtiquetes.next().getObj();
            Iterator<Piulada> iteradorPiulades = etiquetaActual.getPiulades().iterator();

            // Iterem sobre les piulades fetes a l'etiqueta requerida
            while (iteradorPiulades.hasNext()) {
                Piulada piuladaActual = iteradorPiulades.next().getObj();
                if (piuladaActual.getCodiUsuari().equals(usuari))
                {
                    try {
                        llistainterna.afegir(etiquetaActual.getEtiqueta());
                    } catch (LlistaPlena llistaPlena) {
                        System.out.println("Llista plena!");
                        llistaPlena.printStackTrace();
                    }
                }
            }
        }
        return llistainterna;
    }

    @Override
    public int numPiuladesdUsuari(String nomUsuari) {
        int counter = 0;
        Iterator<Etiqueta> iteradorEtiquetes = this.llistaEtiquetes.iterator();

        while (iteradorEtiquetes.hasNext())
        {
            Etiqueta etiquetaActual = iteradorEtiquetes.next().getObj();

            Iterator<Piulada> iteradorPiulades = etiquetaActual.getPiulades().iterator();
            while (iteradorPiulades.hasNext())
            {
                Piulada piuladaActual = iteradorPiulades.next().getObj();
                if (piuladaActual.getCodiUsuari().equals(nomUsuari))
                {
                    counter += 1;
                }
            }

        }
        return counter;
    }

    public static int getDim() {
        return dim;
    }

    public static void setDim(int dim) {
        Etiquetes.dim = dim;
    }

    @Override
    public String toString() {
        String s = "";
        s += "\n";
        if (this.implementation == 1)
        {
            s += "DYNAMIC IMPLEMENTATION";
        }
        else
        {
            s += "STATIC IMPLEMENTATION";
        }
        s += llistaEtiquetes;
        return s;
    }
}
