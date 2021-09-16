<%--
  Created by IntelliJ IDEA.
  User: du
  Date: 2021/9/2
  Time: 14:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="http://libs.baidu.com/jquery/2.0.0/jquery.min.js"></script>
<html>
<head>
    <title>总结springmvc之ajax和mvc收参数 非常重要</title>
</head>
<body>
<div>
    <input type="button" value="ajax 的 01 中传参数 k-结构或者对象" id="ajax01">
    <input type="button" value="ajax 的 02 中传参数 对象" id="ajax02">
    <input type="button" value="ajax 的 03 中传参数 传数组或者集合" id="ajax03">
    <input type="button" value="ajax 的 04 中传参数 传json对象" id="ajax04">
    <input type="button" value="ajax 的 05 中传参数 传 多个对象" id="ajax05">
    <input type="button" value="ajax 的 06 中传参数 传 批量对象传参" id="ajax06">
    <input type="button" value="ajax 的 07 中传参数 传 map传参" id="ajax07">
    <input type="button" value="ajax 的 08 中对象+常用类型 混合，常见于 带参数的分页查询" id="ajax08">
</div>

<script>
    $(function () {
        $("#ajax01").click(function () {
            $.ajax({
                url:"/reg/regByBean",
                type:"POST",
                data:{
                    'adminName':'zhangsan',
                    'adminPwd':'123456789',
                    'adminTime':'2001-03-24 20:23:21'
                },
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                }
            });
        });
        $("#ajax02").click(function () {
            var adminInfo={};
            adminInfo.adminName='zhangsan';
            adminInfo.adminPwd='123456789';
            adminInfo.adminTime='2001-03-24 20:23:21'
        });


        //ajax 传数组，集合，应用的范围是 批量删除
        $("#ajax03").click(function () {
            var ids = new Array();
            ids.push(3);
            ids.push(6);
            ids.push(8);
            ids.push(80);
            //把数组 使用ajax 传递
            $.ajax({
                url:"/houtaidengxiaxie",
                type:"POST",
                data:{'ids':ids},
                dataType:'JSON',
                success:function (res) {
                    console.log(res)
                }
            });
        });
        $("#ajax04").click(function () {
            //json对象 浏览器中看到请求是带颜色的
            var adminInfo = {
                adminName: "小迪",
                adminTime: "2001-03-24 20:23:21",
                adminPwd: "987090",
                loverList: [
                    {
                        name: '小迪',
                        age: 18
                    },
                    {
                        name: '小白',
                        age: 18
                    },
                    {
                        name: '小樱',
                        age: 20
                    }
                ]
            }
            aihao:[4,9,13]
        })
        //var 出来的对象，他是个对象，如果直接传输，www-urlxxx传递的，黑色
        //因为 普通的 请求 是很多个 k -v 结构，后台收取特别麻烦，所有就需要把复杂的对象转换成本json对象
        //然后 后台接收就方便多了，前后端项目，一般都是用json传递！！！
        $.ajax({
            url:'/suibian',
            type:'POST',
            //data:adminInfo,  //普通的 k-v 结构 请求方式是：content-Type：application/x-www-form-urlencoded; charset=UTF-8
            data:JSON.stringify(adminInfo), //变为json对象后，就需要 Context-Type 更变为 application/json; charset=UTF-8
            contentType:"application/json;charset=UTF-8",
            dataType:"json",
            success:function (rs) {
                console.log(rs)
            }
        });
    });
    $("#ajax05").click(function () { //不常见，因为 发货单，上面收货人的对象，下面是产品对象
        $.ajax({
            url:"/api/admin/ajax05",
            type:'post',
            dataType:'json',
            data:{
                'lover.name':'胖迪',
                'lover.age':18,
                'dog.dogId':1002,
                'dog.dogSex':'男',
            },
            success:function (res) {
                console.log(res)
            }
        })
    });
    $("#ajax06").click(function () {
        var loverList=[];
        for (var i =0; i < 5; i++){
            var lover={
                name:"迪迪"+i,
                age:18
            }
            loverList.push(lover)
        }
        //多个对象，就属于复杂，复杂用json
        $.ajax({
            url:'/api/admin/ajax06',
            // type:'GET',  //get请求是无法 传输 json 协议的 和 json数据的！！
            // get请求没有方法体，无法传输json数据
            type:'post',
            data:JSON.stringify(loverList),
            contentType:"application/json;charset=UTF-8",
            success:function (r) {
                console.log(r)
            }
        });
    });
    $("#ajax07").click(function () {
        var map={
            'adminName':'zhangsan',
            'adminPwd':'123456789',
            'adminTime':'2001-03-24 20:23:21'
        }
        $.ajax({
            url:'/api/admin/ajax07',
            type:'post',
            dataType:'json',
            data:JSON.stringify(map),
            success:function (res) {
                console.log(res)
            },
            contentType:"application/json;charset=UTF-8",
        })
    });
    $("#ajax08").click(function () {
        $.ajax({
            url:'/api/admin/ajax08',
            type:'post',
            data:{   //4个kv 是3个参数，name
                'name':'didi',
                'age':29,
                'pageSize':5,
                'page':2
            },
            dataType:'json',
            success:function (r) {
                console.log(r)
            },
        });
    });

</script>

</body>
</html>
