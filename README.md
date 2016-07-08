#Glava (Current Version: 1.5.1)
A Java dialect which is a golfing language. Thanks to PPCG.SE user ConorO'Brien for the new name.

Download the zip, the compiler is in the `latest` folder.

Run the jar with `java -jar glava.jar C:\path\to\file.jg`.

##Shorthands added by Glava:
 - `b|` to `boolean `
 - `c|` to `class `
 - `C|` to `public class `
 - `d|` to `double `
 - `f|` to `float `
 - `f(` to `for(`
 - `F(` to `System.out.printf(`
 - `i|` to `int `
 - `I|` to `import `
 - `m|` to `public static void main (String[] A) {`
 - `M|` to `Math.`
 - `n|` to `new `
 - `N|` to `null `
 - `p|` to `public `
 - `p(` to `System.out.print(`
 - `P(` to `System.out.println(`
 - `r|` to `return `
 - `s|` to `String `
 - `t|` to `try {` (WIP)
 - `v|` to `void `
 - `z|` to import statements for commonly used classes (`java.util.*`, `java.lang.*`, `java.io.*`)
 - `#` to `[]`
 - `$` to `={` for array initialization

##Other Cool Features:
 - If you don't use the `z|` shorthand anywhere in your code, Glava will automatically do it for you at the beginning of the file.
 - If you don't make a class, Glava will automatically surround the entire code in a `Main` class to allow for running. See [here](http://codegolf.stackexchange.com/questions/55422/hello-world/68496#68496) for an example.
 - If you don't make a main method (`m|`), Glava will automatically surround your code in one!
 - You can omit the last `"`, `)`s, `}`s, and `]`s at the end of the program because Glava will automatically fill them in for you!
 - Adding on to the previous feature, when the compiler has to add a `}` to your code, it automatically places a semicolon (`;`) before it.
 - Multiline strings!
