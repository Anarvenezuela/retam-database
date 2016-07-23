
SELECT [Codigo]
      ,[ObjetivoGeneral]
      ,[ObjetivosEspecificos]
      ,[Metodologia]
      ,[FechaInicio]
      ,[Duracion]
      ,[Ubicacion]
      ,[IE]
      ,[CBeneficiada]
      ,[ONG]
      ,[AFinanciadora]
      ,[IGubernamental]
      ,[OReligiosa]
      ,[OtrosIniciativa]
      ,[NinosU]
      ,[NinosR]
      ,[NinosI]
      ,[MujeresU]
      ,[MujeresR]
      ,[MujeresI]
      ,[OtrosSU]
      ,[OtrosSR]
      ,[OtrosSI]
      ,[Concepcion]
      ,[Planificacion]
      ,[Implementacion]
      ,[Difusion]
      ,[Capacitacion]
      ,[Seguimiento]
      ,[OtrosParticipacion]
  FROM [C:\TECNOLOGIASAPROPIADAS\APP_DATA\TECNOLOGIASAPROPIADA.MDF].[dbo].[Objetivos] o
  JOIN [C:\TECNOLOGIASAPROPIADAS\APP_DATA\TECNOLOGIASAPROPIADA.MDF].[dbo].Proyectos p
	ON o.Codigo = p.Cod_Proyecto
  ORDER BY o.Codigo