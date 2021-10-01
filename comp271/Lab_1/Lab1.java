package Lab_1;

public class Lab1 {

    public final static int var1 = 11;
    public static int var2 = 22;

    private final int var3 = 33;
    private int var4 = 44;

    public Lab1() { }

    public Lab1(final int var4) {
        this.var4 = var4;
    }

    public static void main(final String[] args) {
        final Lab1 inst1 = new Lab1();
        final Lab1 inst2 = new Lab1();
        final Lab1 inst3 = inst2;

        System.out.println(inst1.var1 + "       --1");
        System.out.println(inst1.var2 + "       --2");
        System.out.println(inst1.var3 + "       --3");
        System.out.println(inst1.var4 + "       --4");

        System.out.println(inst2.var1 + "       --5");
        System.out.println(inst2.var2 + "       --6");
        System.out.println(inst2.var3 + "       --7");
        System.out.println(inst2.var4 + "       --8");

        inst2.var2 = 23;
        inst2.var4 = 45;

        System.out.println(inst1.var2 + "       --9");
        System.out.println(inst1.var4 + "       --10");

        System.out.println(inst3.var1 + "       --11");
        System.out.println(inst3.var2 + "       --12");
        System.out.println(inst3.var3 + "       --13");
        System.out.println(inst3.var4 + "       --14");

        System.out.println(inst2.method1() + "       --15");
        System.out.println(inst2.method2("hello there") + "       --16");
        System.out.println(inst2.method3(99) + "      --17");
        inst2.method4();
        System.out.println(inst2.var4 + "        --18");

        final Lab1 inst4 = new Lab1(77);
        System.out.println(inst4.var4 + "       --19");

        System.out.println(var1 + "       --20");
        System.out.println(var2 + "       --21");
    }

    public int method1() {
        return var3 + var4;
    }

    public int method2(final String word) {
        return var4 + word.length();
    }

    public int method3(int var4) {
        var4 = 55;
        return var4 + this.var4;
    }

    public void method4() {
        var4 = 0;
    }
}
