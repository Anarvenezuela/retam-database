SELECT  Cod_Proyecto,
        NombreProyecto,
        Pais,
        Cod_InstitucionEjecutora,
        Representante,
        RepresentanteInstitucion,
        NombreProfesional
FROM Proyectos
JOIN Paises ON Proyectos.IdPais = Paises.IdPais
ORDER BY Cod_Proyecto

