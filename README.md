# <s>Java-Golf</s> Glava (Current Version: 1.0.1)
A Java dialect which is a golfing language. Thanks to PPCG.SE user ConorO'Brien for the new name.

Download the zip, the compiler is in the `latest` folder.

Run the jar with `java -jar java-golf.jar C:\path\to\file.jg`. You can also add the `-run` flag to run the java class immediately as well (`java -jar java-golf.jar C:\path\to\file.jg -run`).

##Shorthands added by Java-Golf:
 - `b|` to `boolean `
 - `c|` to `class `
 - `C|` to `public class `
 - `d|` to `double `
 - `f|` to `float `
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

##Other Cool Features:
 - If you don't use the `z|` shorthand anywhere in your code, Java-Golf will automatically do it for you at the beginning of the file.
 - If you don't make a class, Java-Golf will automatically surround the entire code in a `Main` class to allow for running. See [here](http://codegolf.stackexchange.com/questions/55422/hello-world/68496#68496) for an example.
