
public class Data implements Comparable<Data>{
    private int dia;
    private int mes;
    private int any;

    public Data(int dia, int mes, int any) {
        this.dia = dia;
        this.mes = mes;
        this.any = any;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAny() {
        return any;
    }

    public void setAny(int any) {
        this.any = any;
    }

    @Override
    public int compareTo(Data data)
    {
        if (this.any == data.any)
        {
            if (this.mes == data.mes)
            {
                if (this.dia == data.dia)
                {
                    return 0;
                }
                else if (this. dia > data.dia)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
            else if (this.mes > data.mes)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else if (this.any > data.any)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }

    public int compareTo(int dia, int mes, int any)
    {
        if (this.any == any)
        {
            if (this.mes == mes)
            {
                if (this.dia == dia)
                {
                    return 0;
                }
                else if (this. dia > dia)
                {
                    return 1;
                }
                else
                {
                    return -1;
                }
            }
            else if (this.mes > mes)
            {
                return 1;
            }
            else
            {
                return -1;
            }
        }
        else if (this.any > any)
        {
            return 1;
        }
        else
        {
            return -1;
        }
    }
}
