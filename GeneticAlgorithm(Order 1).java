import java.util.*;
public class AlgoritmoGenéticoCruceOrden1 {
    public static void main(String[] args){

        //Declaración de los dos padres con misma longitud
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Carlos");
        arrayList.add("Manuel");
        arrayList.add("Salim");
        arrayList.add("Juan");
        arrayList.add("Mario");
        ArrayList<String> arrayList2 = new ArrayList<>();
        arrayList2.add("Marta");
        arrayList2.add("Adriana");
        arrayList2.add("Naomi");
        arrayList2.add("Alicia");
        arrayList2.add("Carlota");

        ArrayList<ArrayList<String>> hijos = cruceOrdenUno(arrayList,arrayList2);
        //Mostrar por pantalla el resultado del cruce
        System.out.println("Resultado del cruce: ");
        System.out.println("Hijo #1: "+hijos.get(0));
        System.out.println("Hijo #2: "+hijos.get(1));

    }
    public static String devolverStringEspacios(String string){
        String stringEspacios="";
        for (int i = 0; i < string.length(); i++) {
            stringEspacios+=" ";
        }
        return stringEspacios;
    }
    public static void rellenarArrayListStringVacios(ArrayList<String> arrayList, int div1, int div2){
        int i = 0;
        for (String string: arrayList) {
            if(i >= div1 && i <= div2){
                arrayList.set(i,devolverStringEspacios(string));
            }
            i++;
        }
    }

    //Una manera de devolver ambos hijos es devolver un ArrayList de ArrayLists de tipo String para así luego desglosar en uno de los dos indices uno de los dos hijos y viceversa
    public static ArrayList<ArrayList<String>> cruceOrdenUno(ArrayList<String> arrayList, ArrayList<String> arrayList2){
        ArrayList<ArrayList<String>> arrayListConHijos = new ArrayList<>();
        //Mostrar la población inicial
        System.out.println("Población inicial: ");
        System.out.println("Padre #1: "+arrayList);
        System.out.println("Padre #2: "+arrayList2+"\n");

        //Generación aleatoria de la selección:
        Random r = new Random();
        r.setSeed(System.currentTimeMillis());
        int div1 = r.nextInt(arrayList.size());
        int div2 = r.nextInt(arrayList.size()-div1)+div1; //div2 tiene que ser mayor que div1 por eso el +div1 que empuja div1 posiciones el módulo del rango de generación de div2
                                                                //p.e (0,7) -> (2,9) el módulo es el mismo, pero el rango es diferente.

        //división de las dos padres en tres subArrayLists para manejar el cruce eficientemente
        List <String> subArrayIzquierda1 = new ArrayList<>(arrayList.subList(0, div1)); //-> [0,div1)
        List <String> subArrayEntre1 = new ArrayList<>(arrayList.subList(div1,div2+1)); //-> [div1,div2+1) -> [div1,div2]
        List <String> subArrayDerecha1 = new ArrayList<>(arrayList.subList(div2+1,arrayList.size())); // -> [div2+1, array.size() ) -> [div2+1, arrayLastIndex]

        List <String> subArrayIzquierda2 = new ArrayList<>(arrayList2.subList(0, div1)); //-> [0,div1)
        List <String> subArrayEntre2 = new ArrayList<>(arrayList2.subList(div1,div2+1)); //-> [div1,div2+1) -> [div1,div2]
        List <String> subArrayDerecha2 = new ArrayList<>(arrayList2.subList(div2+1,arrayList2.size())); // -> [div2+1, array.size() ) -> [div2+1, arrayLastIndex]

        //Mostrar la selección por pantalla con adición de métodos auxiliares para facilitar la visualización.
        System.out.println("Seleccion de cruce entre la posicion "+(div1)+" ocupada por "+arrayList.get(div1)+" y la posicion "+(div2)+" ocupada por "+arrayList.get(div2));
        rellenarArrayListStringVacios(arrayList,div1,div2);
        System.out.println(arrayList+"\n");

        System.out.println("Seleccion de cruce entre la posicion "+(div1)+" ocupada por "+arrayList2.get(div1)+" y la posicion "+(div2)+" ocupada por "+arrayList2.get(div2));
        rellenarArrayListStringVacios(arrayList2,div1,div2);
        System.out.println(arrayList2+"\n");

        //Reseteo de ambos padres
        arrayList.clear();
        arrayList2.clear();

        //Reordenamiento de los dos padres para conseguir el resultado de los hijos
        arrayList.addAll(subArrayIzquierda2);
        arrayList.addAll(subArrayEntre1);
        arrayList.addAll(subArrayDerecha2);

        arrayList2.addAll(subArrayIzquierda1);
        arrayList2.addAll(subArrayEntre2);
        arrayList2.addAll(subArrayDerecha1);

        //añadimos en el arrayList el resultado de modificar a los padres.
        arrayListConHijos.add(arrayList);
        arrayListConHijos.add(arrayList2);

        return arrayListConHijos;
    }
}
