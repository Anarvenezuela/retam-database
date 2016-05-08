SELECT DISTINCT Cod_InstitucionEjecutora,
    Representante,
    RepresentanteInstitucion,
    NombreProfesional,
    CargoRepresentante,
    CargoRepresentanteInst,
    Profesion

FROM Proyectos p
ORDER BY Cod_InstitucionEjecutora
