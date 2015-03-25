package ro

class KlsBuilder {
    GroovyClassLoader loader
    String name
    Class cls

    def parent
    def imports
    def fields
    def meths

    private void ii(ClassLoader loader) {
        if (parent == null) {
            parent = "Object"
        }

        imports = []
        fields = [:]
        meths = [:]

        if (loader instanceof GroovyClassLoader) {
            this.loader = loader
        } else {
            this.loader = new GroovyClassLoader(loader)
        }
    }

    def KlsBuilder(loader) {
        ii(loader)
    }

    def KlsBuilder(name, loader) {
        this.name = name
        ii(loader)
    }

    def KlsBuilder(name, parent, loader) {
        this.name = name
        this.parent = parent
        ii(loader)
    }

    def setName(String name) {
        this.name = name
    }

    def addImport(Class importClass) {
        imports << "${importClass.getPackage().getName()}" +
                ".${importClass.getSimpleName()}"
    }

    def addField(String name, Class type) {
        fields[name] = type.simpleName
    }

    def addMeth(String name, Closure closure) {
        meths[name] = closure
    }

    def build() {
        def templateText = '''
<%imports.each {%>
import $it\n
<% } %>
class $name extends $parent {
<%fields.each {%>
    $it.value $it.key \n
<% } %>
}
'''
        def data = [name: name, imports: imports, fields: fields, parent: parent]

        def engine = new groovy.text.SimpleTemplateEngine()
        def template = engine.createTemplate(templateText)
        def r = template.make(data)
        cls = loader.parseClass(r.toString())
        meths.each {
            cls.metaClass."$it.key" = it.value
        }
        return cls
    }
}
