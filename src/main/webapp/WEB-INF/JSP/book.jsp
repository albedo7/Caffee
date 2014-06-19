<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>


    <script type="text/javascript" src="http://code.jquery.com/jquery-1.4.4.min.js"></script>
    <script src="resources/booklet/jquery.easing.1.3.js" type="text/javascript"></script>
    <script src="resources/booklet/jquery.booklet.1.1.0.min.js" type="text/javascript"></script>

    <link href="resources/booklet/jquery.booklet.1.1.0.css" type="text/css" rel="stylesheet" media="screen" />
    <link rel="stylesheet" href="resources/css/style.css" type="text/css" media="screen"/>

    <script src="resources/cufon/cufon-yui.js" type="text/javascript"></script>
    <script src="resources/cufon/RodeoExtraBold_400.font.js" type="text/javascript"></script>
    <script src="resources/cufon/Betina_400.font.js" type="text/javascript"></script>
    <script type="text/javascript">
        Cufon.replace('h1,p,.b-counter');
        Cufon.replace('.book_wrapper a', {hover:true});
        Cufon.replace('.title', {textShadow: '1px 1px #C59471', fontFamily:'RodeoExtraBold'});
        Cufon.replace('.reference a', {textShadow: '1px 1px #C59471', fontFamily:'RodeoExtraBold'});
        Cufon.replace('.loading', {textShadow: '1px 1px #000', fontFamily:'RodeoExtraBold'});
    </script>
</head>
    <body>
    <div class="book_wrapper">
        <a id="next_page_button"></a>
        <a id="prev_page_button"></a>
        <div id="loading" class="loading">Loading menu...</div>
        <div id="mybook" style="display:none;">
            <div class="b-load">
                <c:forEach var="meal" items="${mealList}">
                    <div>
                        <h1>${meal.name}</h1>
                        <p>${meal.description}</p>
                        <p class="article">${meal.price}</p>
                        <a href="/add" class="demo">Add to order</a>
                    </div>
                </c:forEach>
                <%--<div>
                    <img src="resources/images/1.jpg" alt=""/>
                    <h1>Витаем в облаках - тема для сайта</h1>
                    <p>В данном уроке с помощью jQuery мы сделаем макет сайта
                        с горизонтальной прокруткой контента и параллаксом смещающихся облаков на фоне.</p>
                    <a href="http://www.ruseller.com/lessons.php?rub=32&id=795" target="_blank" class="article">Урок</a>
                    <a href="http://www.ruseller.com/lessons/les795/demo/demo.html" target="_blank" class="demo">Демонстрация</a>
                </div>--%>

                <!--<div>-->
            </div>
        </div>
    </div>

    <!-- JavaScript -->

    <script type="text/javascript">
        $(function() {
            var $mybook 		= $('#mybook');
            var $bttn_next		= $('#next_page_button');
            var $bttn_prev		= $('#prev_page_button');
            var $loading		= $('#loading');
            var $mybook_images	= $mybook.find('img');
            var cnt_images		= $mybook_images.length;
            var loaded			= 0;
            //Предварительно загружаем все страницы в книжке,
            //а затем вызываем плагин Booklet

            $mybook_images.each(function(){
                var $img 	= $(this);
                var source	= $img.attr('src');
                $('<img/>').load(function(){
                    ++loaded;
                    if(loaded == cnt_images){
                        $loading.hide();
                        $bttn_next.show();
                        $bttn_prev.show();
                        $mybook.show().booklet({
                            name:               null,                            // Имя буклета, которое выводится в заголовке документа
                            width:              800,                             // Ширина контейнера
                            height:             500,                             // Высота контейнера
                            speed:              600,                             // Скорость перехода между страницами
                            direction:          'LTR',                           // Направление организации контента, по умолчанию LTR (left to right - слева направо), может быть RTL  (справа налево)
                            startingPage:       0,                               // Индекс страницы, которая будет выводиться первой
                            easing:             'easeInOutQuad',                 // Метод сглаживания для завершения трансформации
                            easeIn:             'easeInQuad',                    // Метод сглаживания для первой половины трансформации
                            easeOut:            'easeOutQuad',                   // Метод сглаживания для второй половины трансформации

                            closed:             true,                            // Запускаем книгу "закрытой", будут добавлены пустые страницы в начало и конец
                            closedFrontTitle:   null,                            // Используется с опциями "closed", "menu" и "pageSelector", определяет заголовок пустой начальной старницы
                            closedFrontChapter: null,                            // Используется с опциями "closed", "menu" и "chapterSelector", определяет имя главы пустой начальной страницы
                            closedBackTitle:    null,                            // Используется с опциями "closed", "menu" и "pageSelector", определяет заголовок пустой последней страницы
                            closedBackChapter:  null,                            // Используется с опциями "closed", "menu" и "chapterSelector", определяет имя главы пустой конечной страницы
                            covers:             false,                           // Используется с опцией "closed", делает первую и последнюю страницу обложками, без нумерации страниц (если возможно)

                            pagePadding:        10,                              // Отступ для обертки каждой страницы
                            pageNumbers:        true,                            // Выводит номер на каждой странице

                            hovers:             false,                           // Разрешает анимацию предварительного просмотра страниц при наведени курсора мыши, выводятся маленькие изображения предыдущей и следующей страницы
                            overlays:           false,                           // Разрешает навигацию с использованием слоя перекрытия, когда разрешено - ссылки в контексте не будут реагировать на нажатия кнопки мыши
                            tabs:               false,                           // Добавляет закладки вдоль верха страницы
                            tabWidth:           60,                              // Определяем ширину закладок
                            tabHeight:          20,                              // Определяем высоту закладок
                            arrows:             false,                           // Добавляем стрелки поверх кромок книжки
                            cursor:             'pointer',                       // Установка css для курсора для боковой области книжки

                            hash:               false,                           // Разрешает навигацию с использованием хэш строки, например: #/page/1 для страницы 1, будет действовать на все книжки с разрешенной опцией 'hash'
                            keyboard:           true,                            // Разрешает навигацию с использованием клавиш стрелок(влево: предыдущая страница, вправо: следующая)
                            next:               $bttn_next,          			 // Селектор для элемента, который используется как выключатель перехода к следующей странице
                            prev:               $bttn_prev,          			 // Селектор для элемента, который используется как выключатель перехода к предыдущей странице

                            menu:               null,                            // Селектор элемента, который используется как область меню, требуется для 'pageSelector'
                            pageSelector:       false,                           // Разрешает навигацию с помощью выпадающего меню для страниц, требует опции 'menu'
                            chapterSelector:    false,                           // Разрешает навигацию с помощью выпадающего меню глав, определяется атрибутом "rel", требует опции 'menu'

                            shadows:            true,                            // Выводить тени при анимации страниц
                            shadowTopFwdWidth:  166,                             // Ширина тени для верха анимации вперед
                            shadowTopBackWidth: 166,                             // Ширина тени для верха анимации назад
                            shadowBtmWidth:     50,                              // Ширина тени для низа анимации

                            before:             function(){},                    // Возвратная функция, которая вызывается перед выполенением каждой анимации
                            after:              function(){}                     // Возвратная функция, которая вызывается после выполнения каждой анимации
                        });
                        Cufon.refresh();
                    }
                }).attr('src',source);
            });

        });
    </script>
    </body>
</html>
