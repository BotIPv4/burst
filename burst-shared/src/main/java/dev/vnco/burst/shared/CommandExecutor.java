package dev.vnco.burst.shared;

import dev.vnco.burst.shared.context.ChildrenContext;
import dev.vnco.burst.shared.context.Context;
import dev.vnco.burst.shared.context.ContextBase;
import dev.vnco.burst.shared.context.InvokeContext;
import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public abstract class CommandExecutor {

    protected final Context context;

    public void execute(CommandSource<?> source, String[] array) {
        if (checkIfSourceHasPermission(context, source)) {
            return;
        }

        CommandArgs args = new CommandArgs(source, array);

        InvokeContext invokeContext = context.getInvokeContext();

        if (!context.getChildrens().isEmpty()) {

            if (array.length == 0) {
                invokeContext.invoke(args);
                return;
            }

            ChildrenContext childrenContext = context.findChildren(array[0]);

            if (childrenContext == null) {
                source.sendMessage(getArgumentNotFoundMessage());
                return;
            }

            if (checkIfSourceHasPermission(childrenContext, source)) {
                return;
            }

            childrenContext.getInvokeContext().invoke(new CommandArgs(source, Arrays.copyOfRange(array, 1, array.length)));
            return;
        }

        invokeContext.invoke(args);
    }

    public abstract String getArgumentNotFoundMessage();

    public abstract String getNoPermissionsMessage();

    protected boolean checkIfSourceHasPermission(ContextBase contextBase, CommandSource<?> source) {
        if (contextBase.hasPermission()) {
            if (!source.hasPermission(contextBase.getPermission())) {
                source.sendMessage(getNoPermissionsMessage());
                return true;
            }
        }
        return false;
    }

}
