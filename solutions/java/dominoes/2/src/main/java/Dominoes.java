
import java.util.List;
import java.util.ArrayList;

public class Dominoes {

    public List<Domino> formChain(List<Domino> dominos) throws ChainNotFoundException {
        if(dominos.isEmpty()){
            return dominos;
        }else if(dominos.size()==1){
            Domino valor=dominos.get(0);
            if(valor.getRight()==valor.getLeft()){
                return dominos;
            }else{
                throw new ChainNotFoundException("No domino chain found.");
            }
        }
        List <Domino> primer=Ordenada(dominos,true);
        int pripri=primer.get(0).getLeft();
        int ultpri=primer.get(primer.size()-1).getRight();
        if(dominos.size()==primer.size()){
            if(pripri==ultpri){
                return primer;
            }else{
                throw new ChainNotFoundException("No domino chain found.");
            }
        }
        List <Domino> segunda=Ordenada(dominos,false);
        List<Domino> tercera=Ordenada(segunda, true);
        int priter=tercera.get(0).getLeft();
        int ultter=tercera.get(tercera.size()-1).getRight();
        if(segunda.size()>0){
            if(priter!=ultter){
                throw new ChainNotFoundException("No domino chain found.");
            }
            for(int i=0; i<primer.size();i++){
                int val1=primer.get(i).getRight();
                if(priter==val1){
                    for(int k=0;k<tercera.size();k++){
                        primer.add((i+k+1),tercera.get(k));
                    }
                    break;
                }
            }
            if(primer.size()==dominos.size()){
                return primer;
            }
        }
        List<Domino> cuarta=Ordenada(segunda,false);
        if(cuarta.size()>0){
            for(Domino d:cuarta){
                if(d.getLeft()!=d.getRight()){
                    throw new ChainNotFoundException("No domino chain found.");
                }
            }
            for(int i=0;i<primer.size();i++){
                int val2=primer.get(i).getRight();
                for(int j=0;j<cuarta.size();j++){
                    int val3=cuarta.get(j).getLeft();
                    if(val2==val3){
                        primer.add((i+1),cuarta.get(j));
                        cuarta.remove(j);
                        break;
                    }
                }
            }
        }
        if(dominos.size()!=primer.size()){
            throw new ChainNotFoundException("No domino chain found.");
        }
        return primer;
    }
    private List<Domino> Ordenada(List<Domino> datos,boolean order) {
        boolean cadena1 = true;
        boolean cadena2;
        int control1 = 0;
        int control2;
        List<Domino> temporal = new ArrayList<>();
        List<Domino> arreglo = new ArrayList<>();
        for(int i=1;i<datos.size();i++){
            temporal.add(datos.get(i));
        }
        arreglo.add(datos.get(0));
        while (cadena1) {
            if (control1 < (datos.size() - 1)){
                Domino primer = arreglo.get((arreglo.size() - 1));
                control2 = 0;
                cadena2 = true;
                while (cadena2) {
                    Domino segundo = temporal.get(control2);
                    if (primer.getRight() == segundo.getLeft()) {
                        arreglo.add(temporal.get(control2));
                        temporal.remove(control2);
                        cadena2 = false;
                    } else if (primer.getRight() == segundo.getRight()) {
                        Domino temp = new Domino(segundo.getRight(), segundo.getLeft());
                        arreglo.add(temp);
                        temporal.remove(control2);
                        cadena2 = false;
                    } else {
                        control2++;
                    }
                    if (control2 >= temporal.size()) {
                        cadena2 = false;
                    }
                }
                control1++;
            } else {
                cadena1 = false;
            }
        }
        if (order) return arreglo;
        else return temporal;
    }    
}