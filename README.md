# MarkdownTool

*A simple tool for generating html files from (github flavored) markdown.*

## How to build

- get the source
- run ant

## how to use

### html template

The html template is a regular html file with a single line comment where the
markdown is included.

    <html>
    <head />
    <body>
    <!-- MarkdownInclude: README.md -->
    </body>
    </html>

This looks for the file `README.md` in the current directory and replaces the comment with includes the generated html fragment.

If you include a html comment inside your markdown - this will work recursively. Just dont create loops.

### generation

This command prints the html to console.

    java -jar dist/MarkdownTool.jar template.html

Just redirect the output into a file.

    java -jar dist/MarkdownTool.jar template.html > generated.html

Have fun.
