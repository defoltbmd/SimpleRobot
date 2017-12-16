package complexnumber;

public class СomplexNumber extends Object {
    /**
     * real part of a number
     */
    public float re;
    /**
     * imaginary part of a number
     */
    public float im;

    /**
     * Default constructor
     * @param re real part of a number
     * @param im imaginary part of a number
     */
    public СomplexNumber(float re, float im) {
        this.re = re;
        this.im = im;
    }

    /**
     *  Well, it will just calculate hashcode via native jvm method (which is good)
     *  you can read more here https://stackoverflow.com/a/18066516
     * @return hash code of specific complex number
     */
    @Override
    public int hashCode() {
        //Gogled this approach since super.hasCode() didn't worked
        return java.util.Objects.hash(this.re, this.im);
    }

    /**
     * Compares two complex numbers
     * @param obj an object to compare with
     * @return true if real and imaginary parts are equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals(this.getClass())){
            return (this.im == ((СomplexNumber)obj).im && this.re == ((СomplexNumber)obj).re);
        }
        //todo add more conditions
        return false;
    }
}
