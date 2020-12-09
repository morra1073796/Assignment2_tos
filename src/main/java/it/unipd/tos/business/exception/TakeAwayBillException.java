////////////////////////////////////////////////////////////////////
// [Maria] [Morra] [1073796]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business.exception;

public class TakeAwayBillException extends Throwable {
    private final String message;

    public TakeAwayBillException() {
        message = "Troppi item nell'ordine";
    }

    @Override
    public String getMessage() {
        return message;
    }
}