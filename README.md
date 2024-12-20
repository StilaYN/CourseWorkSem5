## Цель работы - разработка информационной системы на высокоуровневом языке программирования.

 

### Постановка задачи:


- Окно аутентификации пользователя (процесс подтверждения личности пользователя) - пример на рисунке 5
- Авторизация пользователя и создание меню, структура которого определяется функциями системы в соответствии с правами пользователя - пример на рисунке 1
- Пункт “Справочники” со списком подпунктов, соответствующим нормативно-справочной информации предметной области (например, города, улицы, банки и др.). Каждый подпункт вызывает форму для работы с соответствующим справочником (просмотр, изменение, добавление, удаление и поиск) - пример на рисунке 4
- Для всех пунктов меню, не являющихся справочниками (в зависимости от прав доступа пользователя) реализовать возможности просмотра, изменения, добавления, удаления и поиска информации из таблицы.
- Пункт “Документы” со списком подпунктов, соответствующим выходным документам (запросам). Каждый пункт открывает форму, показывающую содержание документа для последующего вывода в файл. Предлагается реализовать в данном окне текстовое поле для написания запросов с просмотром результатов в виде таблицы и кнопкой экспорта в файл.
- Окно “Справка” с подпунктами “Содержание” (вызывается оглавление справочной системы), “О программе” (вызывается окно - визитная карточка приложения)
- Окно “Разное” с подпунктами “Настройка” (по желанию реализовать смену языка, увеличение или уменьшение шрифта и др.) и “Сменить пароль” (пример на рисунке 6)
 

### Примечания к программной реализации:

- Пароли должны хешироваться;
- При просмотре таблиц можно отсортировать данные по столбцу по убыванию или по возрастанию;
- Работа с промежуточными таблицами может быть реализована в отдельном пункте меню (с учетом прав доступа пользователя к ним) или через связанные с ними таблицы (отдельными кнопками, осуществляя переход в окно для работы с этими таблицами);
- При просмотре таблиц заменять внешние ключи (id) на таблицы-справочники на названия (например, если у Сотрудника есть внешний ключ на таблицу Город, то заменить id_city на название города);
- Во вкладке Документы подготовить готовые запросы для подготовки выходных документов (например, выборка данных для конкретной накладной или счет-фактуры);
- Проверка на корректность данных (как в вводимых, так и изменяемых), обработка исключений;
- При попытке добавления новых записей в формах должны быть установлены значения по умолчанию.
 
