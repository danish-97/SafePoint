RUN FROM INTELLIJ
-----------------
To run the project from IntelliJ, open the project folder in IntelliJ by File->Open then open the folder safepoint in this directory, and run the class Main. Note that sometimes this doesn't run properly due to a problem initialising the QuantumRender, and if this is the case, open the maven section by View->Tool Windows->Maven, then, SafePoint->Plugins->compiler->compiler:compile
Then SafePoint->Plugins->javafx->javafx:run
