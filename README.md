# MarkdownTool

*A simple tool for generating html files from (github flavored) markdown.*

## Index

- [Whats New](#whats-new)
- [Download](#download)
- [Build Instructions](#build-instructions)
- [Installation Instructions](#installation-instructions)
- [Usage](#usage)
    - [Invocation](#invocation)
    - [Html Template](#html-template)
- [Archive] (#archive)
    - [Old News](#old-news)
    - [Downloads](#downloads)

## Whats New

MarkdownTool Version 1 just begins to exist.

[top](#markdowntool)

## Download

[MarkdownTool.jar](MarkdownTool.jar)

[MarkdownTool-src.zip](MarkdownTool-src.zip)

[MarkdownTool-src-with-dependencies.zip](MarkdownTool-src-with-dependencies.zip)

[top](#markdowntool)

## Build Instructions

- Get the source
- Run `ant`

[top](#markdowntool)

## Installation Instructions

Just place the MarkdownTool.jar somewhere.

Optional you can create a script like

```
#!/bin/sh

java -jar /path/to/MarkdownTool.jar $@

```

and save it as `MarkdownTool` in your path.
Now you can just type `MarkdownTool ...` instead of `java -jar /path/to/MarkdownTool.jar ...` each time.


[top](#markdowntool)

## Usage

### Invocation

    java -jar /path/to/MarkdownTool.jar template.html generated.html

or if you have set up the script above

    MarkdownTool template.html generated.html

*For the sake of laziness I will use the latter version only* ;)

The option `--no-loop` omits recursive processing.

    MarkdownTool --no-loop template.html generated.html

You can use `-` as filename to read from stdin or write to stdout. Happy piping!

Also `MarkdownTool --help` and `MarkdownTool --version` will do a great job.

[top](#markdowntool)

### Html Template

The html template is a regular html file with a single line comment where the
markdown is included.

    <html>
    <head />
    <body>
    <!-- MarkdownInclude: README.md -->
    </body>
    </html>

This looks for the file `README.md` in the current directory and replaces the comment with includes the generated html fragment.

    <html>
    <head />
    <body>
    <!-- MarkdownInclude: README.md -->
    <p>lots of many texts 
    ...
    and stuff<p>
    <!-- MarkdownInclude: another.md -->
    </body>
    </html>

The secound template will work as expected.

If you include a raw html comment inside your markdown - this will work recursively. Just dont create loops.

[top](#markdowntool)

## Archive

### Old News

#### version 1

MarkdownTool Version 1 just begins to exist.

[top](#markdowntool)

### Downloads

#### version 1

[MarkdownTool.jar](SSK@MYLAnId-ZEyXhDGGbYOa1gOtkZZrFNTXjFl1dibLj9E,Xpu27DoAKKc8b0718E-ZteFrGqCYROe7XBBJI57pB4M,AQACAAE/MarkdownTool-1/MarkdownTool.jar)

[MarkdownTool-src.zip](SSK@MYLAnId-ZEyXhDGGbYOa1gOtkZZrFNTXjFl1dibLj9E,Xpu27DoAKKc8b0718E-ZteFrGqCYROe7XBBJI57pB4M,AQACAAE/MarkdownTool-1/MarkdownTool-src.zip)

[MarkdownTool-src-with-dependencies.zip](SSK@MYLAnId-ZEyXhDGGbYOa1gOtkZZrFNTXjFl1dibLj9E,Xpu27DoAKKc8b0718E-ZteFrGqCYROe7XBBJI57pB4M,AQACAAE/MarkdownTool-1/MarkdownTool-src-with-dependencies.zip)

[top](#markdowntool)
