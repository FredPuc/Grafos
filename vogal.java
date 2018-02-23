class vogal{
    public static void main(String[] args) {
        String s = "aaaabdsfh";
        boolean vogal = false; // é consoante!
        char rola;
        for (int i = 0; i < s.length(); ++i) {
            rola = s.toLowerCase().charAt(i);
            vogal = vogal || (
                // é letra?
                ((rola >= 'a' && rola <= 'z')) 
                // é vogal?
                && (rola != 'a' && rola != 'e' && rola != 'i' && rola != 'o' && rola != 'u')
            );
            }
        System.out.print(!vogal+"\n");
    }
}