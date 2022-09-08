<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <link rel="stylesheet" href="/resources/static/main.css" />

</head>
<body>
<h1 class="red">${t.titre}</h1>

<h2>Blocs de compĂ©tences</h2>
<dl>
    <#list blocs as b>
        <dt>${b.titre}</dt>
        <dd>${b.description}</dd>
    </#list>
</dl>
</body>
</html>
