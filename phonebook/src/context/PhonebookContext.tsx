import {createContext, ReactNode, useEffect, useState,useContext} from 'react';
import {IPhonebook} from '@/src/@types/phonebook';

import {api} from '@/src/service/api';

interface PhonebookData {
    handleAddEntry: (phonebook: IPhonebook) => void;
    handleEditEntry: (phonebook: IPhonebook) => void;
    handleDeleteEntry: (index: number, phonebookId: number) => void;
    phonebooks: IPhonebook[];
    open: boolean;
    setOpen: (status: boolean) => void;
    error: ApiError;
    setError: (error: ApiError) => void;
}

interface ApiError {
    error: boolean;
    message: string;
}

interface PhonebookProviderProps {
    children: ReactNode;
}

export const PhonebookContext = createContext({} as PhonebookData);

export function PhonebookProvider({children}: PhonebookProviderProps) {
  const [phonebooks, setPhonebooks] = useState<IPhonebook[]>([]);
  const [open, setOpen] = useState(false);
  const [error, setError] = useState<ApiError>({error: false, message: ''});

  useEffect(() => {
    const handleGetPhonebooks = async () => {
      try {
        const response = await api.get('/phonebooks/search?offset=0&limit=10');
        setPhonebooks(response.data.results);
      } catch (err) {
        console.log(err);
      }
    };
    handleGetPhonebooks();
  }, []);

  const handleAddEntry = async (phonebook: IPhonebook) => {
    try {
      const response = await api.post('/phonebooks/create', phonebook);
      setPhonebooks((prevState) => [...prevState, response.data]);
      setOpen(false);
    } catch (err) {
      setOpen(false);
      setError({error: true, message: err.response.data.message});
    }
  };
  
  const handleEditEntry = async (phonebook: IPhonebook) => {
    try {
      await api.put('/phonebooks/' + phonebook.id + '/update', phonebook);
      const index = phonebooks.findIndex(phonebookItem => phonebookItem.id === phonebook.id);        
      setPhonebooks([
        ...phonebooks.slice(0, Number(index)),
        {...phonebook},
        ...phonebooks.slice(Number(index) + 1)
      ]);
    } catch (err) {
      setOpen(false);
      setError({error: true, message: err.response.data.message});
    }
    setOpen(false);
  };

  const handleDeleteEntry = async (index: number, phonebookId: number) => {
    try {
      await api.delete('/phonebooks/' + phonebookId + '/delete');
      setPhonebooks([
        ...phonebooks.slice(0, Number(index)),
        ...phonebooks.slice(Number(index) + 1)
      ]);
    } catch (err) {
      setError({error: true, message: err.response.data.message});
    }
  };

  return (
    <PhonebookContext.Provider
      value={{
        handleAddEntry,
        handleEditEntry,
        handleDeleteEntry,
        phonebooks,
        setOpen,
        open,
        error,
        setError
      }}
    >
      {children}
    </PhonebookContext.Provider>
  );
}
export const usePhonebook = () => useContext(PhonebookContext);
