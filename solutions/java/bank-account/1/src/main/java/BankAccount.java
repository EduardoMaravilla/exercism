import java.util.Map;
import java.util.HashMap;
import java.util.Random;

class BankAccount{
    private Map<String, Integer> account = new HashMap<String, Integer>();
    private String name;
    
    public void open(){
        name = "";
        Random random = new Random();
        do {
           name = String.valueOf(random.nextInt(100));
        } while(account.containsKey(name));
        account.put(name, 0); 
    }
    
    public synchronized void deposit(int deposito) throws BankAccountActionInvalidException {
        if(!account.containsKey(name)) {
           throw new BankAccountActionInvalidException("Account closed"); 
        }
        if(deposito < 1) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        int saldo = account.get(name);
        saldo += deposito;
        account.put(name, saldo);
    }
    
    public synchronized void withdraw(int cobro) throws BankAccountActionInvalidException {
        if(!account.containsKey(name)) {
           throw new BankAccountActionInvalidException("Account closed"); 
        }
        if(cobro < 1) {
            throw new BankAccountActionInvalidException("Cannot deposit or withdraw negative amount");
        }
        int saldo = account.get(name);
        if(saldo == 0) {
           throw new BankAccountActionInvalidException("Cannot withdraw money from an empty account"); 
        } else if(cobro > saldo) {
           throw new BankAccountActionInvalidException("Cannot withdraw more money than is currently in the account");  
        }
        saldo -= cobro;
        account.put(name, saldo);   
    }
    
    public synchronized int getBalance()throws BankAccountActionInvalidException{
        if(!account.containsKey(name)) {
           throw new BankAccountActionInvalidException("Account closed"); 
        }
        int saldo = account.get(name);
        return saldo;
    }
    
    public void close(){
        account.remove(name);
    }
}
