layui.use(['element', 'form', 'layer', 'upload'], function () {
    var $ = layui.jquery;
    var element = layui.element; //加载element模块
    var form = layui.form; //加载form模块
    var layer = layui.layer; //加载layer模块
    var upload = layui.upload;  //加载upload模块

    /* 侧边栏开关 */
    $(".side-toggle").on("click", function (e) {
        e.preventDefault();
        var to = $(".layui-layout-admin");
        to.toggleClass("layui-side-shrink");
        to.attr("toggle") === 'on' ? to.attr("toggle", "off") : to.attr("toggle", "on");
    });
    $(".layui-side").on("click", function () {
        var to = $(".layui-layout-admin");
        if (to.attr("toggle") === 'on') {
            to.attr("toggle", "off");
            to.removeClass("layui-side-shrink");
        }
    });

    /* 最大化窗口 */
    $(".timo-screen-full").on("click", function (e) {
        e.preventDefault();
        if (!$(this).hasClass("full-on")) {
            var docElm = document.documentElement;
            var full = docElm.requestFullScreen || docElm.webkitRequestFullScreen ||
                docElm.mozRequestFullScreen || docElm.msRequestFullscreen;
            "undefined" !== typeof full && full && full.call(docElm);
        } else {
            document.exitFullscreen ? document.exitFullscreen()
                : document.mozCancelFullScreen ? document.mozCancelFullScreen()
                : document.webkitCancelFullScreen ? document.webkitCancelFullScreen()
                    : document.msExitFullscreen && document.msExitFullscreen()
        }
        $(this).toggleClass("full-on");
    });

    /* 新建或切换标签栏 */
    var tabs = function (url) {
        var item = $('[lay-url="' + url + '"]');
        if (url !== undefined && url !== '#' && item.length > 0) {
            var bootLay = $('[lay-id="' + url + '"]');
            if (bootLay.length === 0) {
                var title = item.attr("lay-icon") === 'true' ? item.html()
                    : item.children(".layui-nav-title").text();
                //var iframeUrl = (window.location.pathname + url).replace('//','/');
				var iframeUrl = url;
                element.tabAdd('iframe-tabs', {
                    title: title
                    , content: '<iframe src="' + iframeUrl + '" frameborder="0" class="layui-layout-iframe"></iframe>'
                    , id: url
                });
            }
            element.tabChange('iframe-tabs', url);
        }
    };

    /* 监听导航栏事件，实现标签页的切换 */
    element.on("nav(layui-nav-side)", function ($this) {
        var url = $this.attr('lay-url');
        tabs(url);
    });

    /* 监听标签栏事件，实现导航栏高亮显示 */
    element.on("tab(iframe-tabs)", function () {
        var layId = $(this).attr("lay-id");
        $(".layui-side .layui-this").removeClass("layui-this");
        $('[lay-url="' + layId + '"]').parent().addClass("layui-this");
        // 改变地址hash值
        location.hash = this.getAttribute('lay-id');
    });

    /* 监听hash来切换选项卡*/
    window.onhashchange = function (e) {
        var url = location.hash.replace(/^#/, '');
        var index = $(".layui-layout-admin .layui-side .layui-nav-item")[0];
        $(index).children("a").attr("lay-icon", "true");
        if (url === "" || url === undefined) {
            url = $(index).children("[lay-url]").attr("lay-url");
        }
        tabs(url);
    };
    window.onhashchange();

    /* 初始化时展开子菜单 */
    $("dd.layui-this").parents(".layui-nav-item")
        .addClass("layui-nav-itemed");

    /* 刷新iframe页面 */
    $(".refresh-btn").click(function () {
        location.reload();
    });

    /* 添加/修改弹出层 */
    $(document).on("click", ".open-popup, .open-popup-param", function () {
        var title = $(this).data("title");
        var url =$(this).attr("data-url");

        var size = ['600px', '550px'];
        if ($(this).attr("data-size") !== undefined) {
            size = $(this).attr("data-size").split(",");
            size = [size[0] + 'px', size[1] + 'px'];
        }
        window.layerIndex = layer.open({
            type: 2,
            title: title,
            shadeClose: true,
            maxmin: true,
            area: size,
            content: [url, 'on']
        });
    });


    /* 关闭弹出层 */
    $(".close-popup").click(function (e) {
        e.preventDefault();
        parent.layer.close(window.parent.layerIndex);
    });

    /* 下拉按钮组 */
    $(".btn-group").click(function (e) {
        e.stopPropagation();
        $this = $(this);
        $this.toggleClass("show");
        $(document).one("click", function () {
            if ($this.hasClass("show")) {
                $this.removeClass("show");
            }
        });
    });

    // 展示数据列表-多选框
    var thcheckbox = $(".timo-table th .timo-checkbox :checkbox");
    thcheckbox.on("change", function () {
        var tdcheckbox = $(".timo-table td .timo-checkbox :checkbox");
        if (thcheckbox.is(':checked')) {
            tdcheckbox.prop("checked", true);
        } else {
            tdcheckbox.prop("checked", false);
        }
    });

    // 检测列表数据是否为空
    var timoTable = $(".timo-table tbody");
    if (timoTable.length > 0) {
        var children = timoTable.children();
        if (children.length === 0) {
            var length = $(".timo-table thead th").length;
            var trNullInfo = "<tr><td class='timo-table-null' colspan='"
                + length + "'>没有找到匹配的记录</td></tr>";
            timoTable.append(trNullInfo);
        }
    }

    /* 展示列表数据搜索 */
    $(document).on("click", ".timo-search-btn", function () {
        paramSkip();
    });
    /* 改变显示页数 */
    $(document).on("change", ".page-number", function () {
        paramSkip();
    });


});