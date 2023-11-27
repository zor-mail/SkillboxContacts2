package org.skillbox;

import org.springframework.stereotype.Component;
import java.io.IOException;
import java.util.Scanner;

enum COMMANDS {
    NONE, LIST, NEW, DEL, SAVE, EXIT
}

@Component
public class ContactsWorker {
    private final ContactsServ contactsServ;

    public ContactsWorker(ContactsServ contactsServ) {
        this.contactsServ = contactsServ;
    }

    private COMMANDS currentCommand;
    private Boolean inputOrCommand = false;
    public Scanner scanner;

    public void initApp() throws IOException {
        runInputInteraction();
    }

    void showMessageByCommandType() {
        if (this.currentCommand == null)
            return;
        String message = switch (this.currentCommand) {
            case NONE -> "\n\t*** Введите команду для получения или добавления информации ***";
            case LIST -> "\t\t\t*** Список контактов ***";
            case NEW -> "\t Введите через ';' 'Фамилию Имя Отчество'  Телефон  Емейл ";
            case DEL -> "\t*** Введите email контакта, который хотите удалить ***";
            case SAVE -> "\t*** Контакты сохранены в файл saved-contacts.txt ***";
            case EXIT -> "\t*** Выйти из приложения? ( Y / N ) ***";
        };
        System.out.println(message);
    }

    private COMMANDS getCommandObject(String commandString) {
        COMMANDS command = null;
        try {
            command = COMMANDS.valueOf(commandString.toUpperCase());
        } catch (IllegalArgumentException ex) {
            System.out.println("Команда не распознана. Введите одну из команд: LIST, NEW, DEL, SAVE, EXIT");
            this.currentCommand = COMMANDS.NONE;
        }
        return command;
    }

    private void executeCommand() throws IOException {
        if (this.currentCommand == null || this.currentCommand == COMMANDS.NONE) {
            return;
        }
        switch (this.currentCommand) {
            case LIST -> contactsServ.list();
            case SAVE -> contactsServ.save();
        }
    }

    private void executeCommandWithParam(String param) throws IOException {
        if (this.currentCommand == null || this.currentCommand == COMMANDS.NONE) {
            return;
        }
        switch (this.currentCommand) {
            case NEW -> contactsServ.add(param);
            case DEL -> contactsServ.delete(param);
            case EXIT -> exitProgram(param);
        }
    }

    private Boolean isCommandWithParams() {
        return this.currentCommand == COMMANDS.NEW || this.currentCommand == COMMANDS.DEL ||
                this.currentCommand == COMMANDS.EXIT;
    }

    private void runInputInteraction() throws IOException {
        String inputString;
        this.scanner = new Scanner(System.in, "UTF-8");
        System.out.println("\t\t*** Приложение Контакты готово к работе ***\n");
        this.currentCommand = COMMANDS.NONE;
        while (true) {
            if (this.currentCommand == COMMANDS.NONE)
                showMessageByCommandType();
            inputString = getLineFromScanner();

            if (this.inputOrCommand){ // введен параметр для команды
                executeCommandWithParam(inputString);
                this.currentCommand = COMMANDS.NONE;
                this.inputOrCommand = false;
            } else { // введена команда
                this.currentCommand = getCommandObject(inputString);
                showMessageByCommandType();
                executeCommand();
                this.inputOrCommand = isCommandWithParams();
                if(!this.inputOrCommand)
                    this.currentCommand = COMMANDS.NONE;
            }
        }
    }

    private String getLineFromScanner() {
        String input;
        if (this.scanner.hasNextLine()) {
            input = this.scanner.nextLine();
        } else {
            System.out.println("Некорректный ввод! Введите, пожалуйста, строку в соответствии с инструкцией");
            return getLineFromScanner();
        }
        return input.trim();
    }

    private void exitProgram(String param) {
        if (param.toUpperCase().equals("Y")) {
            this.currentCommand = COMMANDS.EXIT;
            this.scanner.close();
            System.exit(0);
        }
    }
}
