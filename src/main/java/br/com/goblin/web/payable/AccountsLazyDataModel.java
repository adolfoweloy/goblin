package br.com.goblin.web.payable;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.goblin.domain.account.AccountPayable;

public class AccountsLazyDataModel extends LazyDataModel<AccountPayable> {
    private static final long serialVersionUID = 1L;

    @Override
    public int getPageSize() {
        // TODO Auto-generated method stub
        return super.getPageSize();
    }

    @Override
    public int getRowCount() {
        // TODO Auto-generated method stub
        return super.getRowCount();
    }

    @Override
    public AccountPayable getRowData() {
        // TODO Auto-generated method stub
        return super.getRowData();
    }

    @Override
    public Object getRowKey(AccountPayable object) {
        // TODO Auto-generated method stub
        return super.getRowKey(object);
    }

    @Override
    public List<AccountPayable> load(int first, int pageSize, String sortField,
            SortOrder sortOrder, Map<String, Object> filters) {
        // TODO Auto-generated method stub
        return super.load(first, pageSize, sortField, sortOrder, filters);
    }


}
