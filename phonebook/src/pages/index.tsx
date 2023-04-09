import React, { useState } from 'react';
import {
  Button,
} from '@material-ui/core';
import PhonebookLogo from '@/src/components/PhonebookLogo';
import styles from '../styles/PhonebookList.module.css';
import { IPhonebook } from '@/src/@types/phonebook';
import { PhonebookModal } from '@/src/components/PhonebookModal';
import { usePhonebook } from '@/src/context/PhonebookContext';
import { PhonebookList } from '../components/PhonebookList';
import { PhonebookBar } from '../components/PhonebookBar';
import { Alert } from '@mui/material';

function Phonebook() {
  const { setOpen, open, error, setError } = usePhonebook();
  const [query, setQuery] = useState('');
  const [isEditing, setIsEditing] = useState(false);
  const [phonebook, setPhonebook] = useState<IPhonebook>();

  const handleQueryChange = (event: React.ChangeEvent<HTMLTextAreaElement | HTMLInputElement>) => {
    setQuery(event.target.value);
  };

  const handleEditClick = (item: IPhonebook) => {
    setOpen(true);
    setIsEditing(true);
    setPhonebook(item);
  };

  return (
    <>
      {
        error.error && 
          <Alert 
            onClose={() => setError({error: false, message: ''})} 
            variant="filled" 
            severity="error">{error.message}
          </Alert>
      }
      <div className={styles.main_container}>
        <div className={styles.title}>
          <PhonebookLogo />
          <h1>Phonebook App</h1>
        </div>
        <div className={styles.contacts_row}>
          <p className={styles.contacts}>Contacts</p>
          <Button variant="contained" color="primary" onClick={() => {
            setIsEditing(false);
            setOpen(true);
          }}>
            + Add Contact
          </Button>
        </div>
        <PhonebookBar query={query} handleQueryChange={handleQueryChange} />
        <PhonebookList query={query} handleEditClick={handleEditClick} />
      </div>
      <PhonebookModal
        open={open}
        setOpen={() => setOpen(!open)}
        setIsEditing={() => setIsEditing(!isEditing)}
        isEditing={isEditing}
        data={phonebook!}
      />
    </>
  );
}

export default Phonebook;
