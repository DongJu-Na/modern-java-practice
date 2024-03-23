package org.ndj;

public class TextBlock {

    public static void main(String[] args) {
        // old way
        String oldMultiLineString = "<header>\n" +
                "\t<h1>Welcome DJ</h1>\n" +
                "</header>";

        // new way
        String newMultiLineString = """
                <header>
                    <h1>Welcome DJ</h1>
                </header>
            """;

        System.out.println(oldMultiLineString);
        System.out.println(newMultiLineString);

    }

}
