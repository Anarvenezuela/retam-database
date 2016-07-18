
SELECT b.[NombreInstitucion]
      , b.[Cod_Proyecto]
      , [Cod_Beneficiario]
  FROM [C:\TECNOLOGIASAPROPIADAS\APP_DATA\TECNOLOGIASAPROPIADA.MDF].[dbo].[Beneficiarios] b
  JOIN [C:\TECNOLOGIASAPROPIADAS\APP_DATA\TECNOLOGIASAPROPIADA.MDF].[dbo].[Proyectos] p
	ON b.Cod_proyecto = p.Cod_proyecto
  ORDER BY [Cod_Beneficiario]