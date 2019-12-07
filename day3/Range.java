public class Range {
    public int nXmax = 0;
    public int nXmin = 0;
    public int nYmax = 0;
    public int nYmin = 0;

    public Range () {
        
    }
    
    public void setRange(int nXmin, int nXmax, int nYmin, int nYmax) {
        this.nXmin = nXmin;
        this.nXmax = nXmax;
        this.nYmin = nYmin;
        this.nYmax = nYmax;
    }
    
}
