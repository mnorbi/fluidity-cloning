fluidity-cloning
================

Customization of the popular https://code.google.com/p/cloning/ library.

The cloning behavior could be customized with different annotations on the source
class.

For examples take a look at the:
- [CloneTarget](https://github.com/mnorbi/fluidity-cloning/blob/master/cloning/fluidity-cloning/src/test/java/com/innometa/fluidity/cloning/CloneTarget.java)
- [ClonerTest](https://github.com/mnorbi/fluidity-cloning/blob/master/cloning/fluidity-cloning/src/test/java/com/innometa/fluidity/cloning/ClonerTest.java)
classes.

There are two customized implementation:
- [Default Cloner](https://github.com/mnorbi/fluidity-cloning/blob/master/cloning/fluidity-cloning/src/main/java/com/innometa/fluidity/cloning/Cloner.java)
- [Hibernate Cloner](https://github.com/mnorbi/fluidity-cloning/blob/master/cloning/fluidity-cloning-hibernate/src/main/java/com/innometa/fluidity/cloning/hibernate/cloning/Cloner.java)

Credits:
- Kostantinos Kougios
- Norbert Madarasz