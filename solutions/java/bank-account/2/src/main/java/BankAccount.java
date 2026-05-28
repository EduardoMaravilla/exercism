import java.util.Map;
import java.util.HashMap;
import java.util.Random;
class BankAccount{
    private final Map<String, Integer> account = new HashMap<String, Integer>();
    private String name;

    public void open() throws BankAccountActionInvalidException {
        if (!this.account.isEmpty()){
            throw new BankAccountActionInvalidException("Account already open");
        }
        this.name = "";
        Random random = new Random();
        do {
            this.name = String.valueOf(random.nextInt(100));
        } while(account.containsKey(this.name));
        account.put(this.name, 0);
    }

    public synchronized void deposit(int deposito) throws BankAccountActionInvalidException {
        if(!account.containsKey(this.name)) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if(deposito < 1) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        int saldo = account.get(this.name);
        saldo += deposito;
        account.put(this.name, saldo);
    }

    public synchronized void withdraw(int cobro) throws BankAccountActionInvalidException {
        if(!account.containsKey(this.name)) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        if(cobro < 1) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        int saldo = account.get(this.name);
        if(saldo == 0) {
            throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account");
        } else if(cobro > saldo) {
            throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");
        }
        saldo -= cobro;
        account.put(this.name, saldo);
    }

    public synchronized int getBalance()throws BankAccountActionInvalidException{
        if(!account.containsKey(this.name)) {
            throw new BankAccountActionInvalidException("Account closed");
        }
        return account.get(this.name);
    }

    public void close() throws BankAccountActionInvalidException {
        if (this.account.isEmpty()){
            throw new BankAccountActionInvalidException("Account not open");
        }
        account.remove(this.name);
    }
}