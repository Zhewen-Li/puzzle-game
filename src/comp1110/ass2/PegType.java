package comp1110.ass2;
//Created by Zhewen Li, then mostly fixed by Saffron Bannister
public enum PegType {

    i,
    j,
    k,
    l;

    public static PegType getType(char ch) {
        switch(ch){
            case 'i': return i;
            case 'j': return j;
            case 'k': return k;
            case 'l': return l;

        }
        return null;
    }

    // The char used to encode peg colour
    public String getColor(){
        switch(this){
            case i: return "red";
            case j: return "blue";
            case k: return "green";
            case l: return "yellow";
        }
        return null;
    }

}


