<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="${backEndUrl}/css/main.css" />
    <meta charset="UTF-8" />
</head>
<body>
<h1 class="rouge">Bulletin d'évaluation</h1>
<h1>Titre Professionel</h1>
<h1 class="rouge">${titrePro.titre}</h1>

<p>nom............ ${etudiant.nom}</p>
<p>prenom........ ${etudiant.prenom}</p>
<p>Annee détude...... ${promotion.dateDebut}</p>
<p>Filière.............${titrePro.titre}</p>

<table>
    <thead>
    <tr>
        <th colspan="1">bloc</th>
        <th colspan="1">Moyenne Etudiant</th>
        <th colspan="1">Moyenne Promotion</th>
    </tr>
    </thead>
    <tbody>

    <#list frontPdfDtos as f >

    <tr>
        <td>${f.blocComp.titre}
            <br>
            <#list f.competences as c >
                ${c.titre}
            </#list>
        </td>
        <td>${f.moyEtuByBloc}</td>
        <td>${f.moyPromoByBloc}</td>
    </tr></#list>

    </tbody>
</table>

<p>Moyenne Generale eudiant  : ${moyenneGeneralEtudiant}  </p>
<p>Moyenne Generale promo   : ${moyenneGeneralPromotion}  </p>


</body>
</html>
