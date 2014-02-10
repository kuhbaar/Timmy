Readme - Template für UE Multimedia Projekte
==============================================

Die Folgende Verzeichnisstruktur ist einzuhalten:

Die Verzeichnisstruktur enthält die standard Verzeichnisse die von Eclipse für ein Android Projekt angelegt werden.

- Im Root-Verzeichnis: "[Gruppenname]" (z.B. "A05")  wird das Eclipse Projekt gespeichert (Dateien: ".project", ".classpath", "AndroidManifest.xml", und ev. weitere von Eclipse angelegte Dateien)
- "src" enthält alle Java Dateien (*.java)
- "res" enthält alle Bilder, Sounds und Videos die zu dem Spiel gehören
- "doc" enthält die javadoc Dokumentation
- "bin" enthält das fertig kompilierte Spiel (.apk File)

- Im Ordner "intern" können Dateien abgelegt werden, die nicht direkt zum Spiel gehören oder die noch nicht fertig sein (z.B. Entwürfe, Skizzen, unfertige Bilddateien, etc.)

Erste Schritte:
===============

Zu Beginn enpfiehlt es sich das Root-Verzeichnis in einen lokalen Ordner auf der Festplatte auszuchecken. Anschließend legt man ein Eclipse Projekt im Root-Verzeichnis an. Eclipse verwendet dann die Verzeichnisse "src", "res" und "deployed" wie beabsichtigt. Anschließend kann man die Projektdateien dem Repository hinzufügen (z.B. TortoiseSVN -> "Add...") und anschließend hochladen ("SVN commit"). Mit "SVN Update" kann das andere Gruppenmitglied die neuen Dateien dann herunterladen.


Wichtige Hinweise:
==================

- Niemals Dateien die unter Versionskontrolle sind direkt im Explorer löschen, umbenennen oder verschieben. SVN verliert dann die Referenz darauf und SVN wird inkonsistent. Zum Löschen und Umbenennen gibt es eigene Befehle im SVN-Kontextmenü ("Delete" und "Rename"). Nur diese Befehle gewährleisten ein konsistentes Resposiroty.

- Für die UE Multimedia gilt eine Dategrößenbegrenzung von 10MB. Größere Files können und sollen nicht hochgeladen werden (dafür ist SVN nicht gedacht). Große Dateien wie z.B. unkomprimierte Videos können über andere Kommunikationswege (z.B. Skype, Dropbox, etc.) ausgetauscht werden. 

