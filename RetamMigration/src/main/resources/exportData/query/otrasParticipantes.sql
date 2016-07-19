SELECT [CodigoOtras]
      ,o.[NombreInstitucion]
      ,[Participacion]
      ,[Codigo]
  FROM [C:\TECNOLOGIASAPROPIADAS\APP_DATA\TECNOLOGIASAPROPIADA.MDF].[dbo].[OtrasParticipantes] o
  JOIN [C:\TECNOLOGIASAPROPIADAS\APP_DATA\TECNOLOGIASAPROPIADA.MDF].[dbo].Proyectos p
	ON o.Codigo = p.Cod_Proyecto
  WHERE CodigoOtras IS NOT NULL
  ORDER BY CodigoOtras
