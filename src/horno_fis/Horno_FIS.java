package horno_fis;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
//import net.sourceforge.jFuzzyLogic.rule.Rule;
//import net.sourceforge.jFuzzyLogic.rule.Variable;

public class Horno_FIS {

    public static void main(String[] args) {
        
        // Carga el archivo de lenguaje de control difuso 'FCL'
        String fileName = "src/tipper_fis/Tipper_FCL.fcl";
        FIS fis = FIS.load(fileName, true);
        
        // En caso de error
        if (fis == null) {
            System.err.println("No se puede cargar el archivo: '" + fileName + "'");
            return;
        }
        
        // Establecer las entradas del sistema
        fis.setVariable("servicio", 3);
        fis.setVariable("comida", 6);

        // Inicia el funcionamiento del sistema
        fis.evaluate();

        // Muestra los gráficos de las variables de entrada y salida
        JFuzzyChart.get().chart(fis.getFunctionBlock("tipper"));
        
        /*
        // Muestra el conjunto difuso sobre el que se calcula el COG
        Variable tip = fis.getVariable("propina");
        JFuzzyChart.get().chart(tip, tip.getDefuzzifier(), true);
        */
             
        // Imprime el valor concreto de salida del sistema
        double salida = fis.getVariable("propina").getLatestDefuzzifiedValue();
        System.out.println("Para los valores de entrada la propina sugerida es: " + String.format("%.1f", salida) + "%\n");
        
        /*
        // Muestra las reglas y el valor de salida de cada una despues de aplicar las operaciones lógicas
        fis.getFunctionBlock("tipper").getFuzzyRuleBlock("No1").getRules().forEach(r -> {
            System.out.println(r);
        });
        System.out.println();
        */
        
        /*
        // Muestra el grado de pertenencia de la variable de salida a cada CD
        double pertenenciaBaja = fis.getVariable("propina").getMembership("baja");
        double pertenenciaPromedio = fis.getVariable("propina").getMembership("promedio");
        double pertenenciaGenerosa = fis.getVariable("propina").getMembership("generosa");
        
        System.out.println("La propina es baja con grado de pertenencia " + pertenenciaBaja);
        System.out.println("La propina es promedio con grado de pertenencia " + pertenenciaPromedio);
        System.out.println("La propina es generosa con grado de pertenencia " + pertenenciaGenerosa);
        */
    }
    
}
