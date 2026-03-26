package combate;

public class ReporteAtaque {

    private String nombreAtacante;
    private String nombreMovimiento;
    private int dmg;
    private String nombreDefensor;
    private int hpRestante;
    private boolean defensorDebilitado;
    private String nombreSiguiente;

    public ReporteAtaque(String nombreAtacante, String nombreMovimiento, int dmg, String nombreDefensor, int hpRestante, boolean defensorDebilitado, String nombreSiguiente) {
        this.nombreAtacante = nombreAtacante;
        this.nombreMovimiento = nombreMovimiento;
        this.dmg = dmg;
        this.nombreDefensor = nombreDefensor;
        this.hpRestante = hpRestante;
        this.defensorDebilitado = defensorDebilitado;
        this.nombreSiguiente = nombreSiguiente;
    }

    public String getNombreAtacante(){
        return nombreAtacante;
    }
    public String getNombreMovimiento(){ 
        return nombreMovimiento;
    }
    public int getDmg(){
        return dmg; 
    }
    public String getNombreDefensor(){ 
        return nombreDefensor; 
    }
    public int getHpRestante(){ 
        return hpRestante; 
    }
    public boolean isDefensorDebilitado(){ 
        return defensorDebilitado; 
    }
    public String getNombreSiguiente(){ 
        return nombreSiguiente; 
    }
}
