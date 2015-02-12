Не забывайте о:

1) Ваш класс должен наследоваться от класса ISolver. В нём есть public метод solve(double[][] A, double[] b), но переопределять надо другой - solve(Matrix A, Matrix b) (пример в JamaSolver.java). Все проверки от дурака уже сделаны за вас

2) Пишите решатель в отдельном пакете. Хотя бы потому что protected методы класса видны снаружи для любого другого класса в том же пакете, можете запутаться

3) Если точность вашего метода не ограничивается лишь погрешностью вычислений, добавьте её в качестве параметра конструктора класса


Как подготовить тесты:
Edit Configurations -> Add New -> JUnit
Test kind: Class / All in package в зависимости от того, хотите запускать тесты по отдельности или все сразу


ISolverTest - базовый класс для тестов. В нём потребуется изменить поле testingClass на ваш класс
Наследующиеся от него классы должны иметь метод data() возвращающий коллекцию, состоящую из Object[]{A, b}, где A, b - входные данные одного теста