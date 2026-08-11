# GuessNumberGameConsole (DevOps version)

Консольная версия игры «Угадай число» для демонстрации деплоя Java‑приложения в Docker и Kubernetes.

Этот проект показывает адаптацию GUI‑приложения под контейнерную среду и оркестрацию.

## Особенности
- Полностью консольный интерфейс Переписано с JOptionPane на консольный ввод/вывод (Scanner + System.out).
- Валидация ввода имени. Проверка на пустое значение, только цифры, первая буква заглавная.
- Валидация ввода чисел. Проверка на диапазон, обработка ошибок ввода.
- Псевдографика для заголовков и поздравлений.
- Работает на Java 8 и выше (максимальная совместимость).
- DevOps версия. Готовое окружение для Docker и Kubernetes (Job).

## Локальный запуск

1.  Убедитесь, что установлен JDK 8 или выше.
2.  Скомпилируйте проект:
    ```bash
    javac -source 1.8 -target 1.8 GuessNumberGameConsole.java
    ```
3.  Запустите игру:
    ```bash
    java GuessNumberGameConsole
    ```

### Сборка Docker-образа

1.  В папке с проектом (где лежит Dockerfile и .java файл):
    ```bash
    docker build -t guess-number-console:v1 .
    ```
2.  Запуск в интерактивном режиме:
    ```bash
    docker run -it guess-number-console:v1
    ```

## Деплой в Kubernetes (Minikube)

Так как приложение интерактивное, оно развернуто как **Job** для проверки запуска и логов.

1.  Активируйте среду Docker для Minikube:
    ```bash
    eval $(minikube docker-env)
    ```
2.  Соберите образ (он автоматически попадет в кластер):
    ```bash
    docker build -t guess-number-console:v1 .
    ```
3.  Примените манифест:
    ```bash
    kubectl apply -f guess-number-job.yaml
    ```
4.  Проверьте статус и логи:
    ```bash
    kubectl get pods
    kubectl logs -f job/guess-number-job
    ```
    *Примечание: Игра будет ждать ввода пользователя в логах. Это демонстрирует корректность запуска контейнера.*

## Автоматизация

В проекте присутствует скрипт `deploy-game.sh`, который автоматизирует весь пайплайн:
1.  Компиляция Java.
2.  Настройка Docker для Minikube.
3.  Сборка образа.
4.  Деплой Job в K8s.
5.  Просмотр логов.

Запуск:
```bash
chmod +x deploy-game.sh
./deploy-game.sh