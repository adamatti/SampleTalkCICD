package spring

beans {
	xmlns context:"http://www.springframework.org/schema/context"

	context."component-scan"("base-package" : "adamatti")

	//importBeans('classpath:spring/camel.groovy')
}
