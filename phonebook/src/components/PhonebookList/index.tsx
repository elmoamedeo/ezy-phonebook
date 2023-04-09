import styles from '../../styles/PhonebookList.module.css';
import { usePhonebook } from '@/src/context/PhonebookContext';
import { IPhonebook } from '@/src/@types/phonebook';
import DeleteIcon from '@material-ui/icons/Delete';
import EditIcon from '@material-ui/icons/Edit';
import PhoneIcon from '@material-ui/icons/Phone';
import {
  IconButton,
  List,
  ListItem,
  ListItemSecondaryAction,
  ListItemText
} from '@material-ui/core';

interface IPhonebookList {
  query: string;
  handleEditClick: (item: IPhonebook) => void;
}

export const PhonebookList = ({ query, handleEditClick }: IPhonebookList) => {

  const { phonebooks, handleDeleteEntry } = usePhonebook();

  return (
    <div className={styles.contact_list_container}>
      <List
        style={{
          display: 'flex',
          flexDirection: 'column',
          gap: '10px',
        }}
      >
        {phonebooks && phonebooks
          .filter((phonebook) => phonebook.lastName.toLowerCase().includes(query.toLowerCase()))
          .map((phonebook, index: number) => (
            <ListItem
              key={phonebook.id}
              style={{
                border: '2px solid lightGrey',
                padding: '10px'
              }}
            >
              <ListItemText
                secondaryTypographyProps={{
                  style:{
                    alignItems:'center',
                    display:'flex',
                    gap:10
                  }
                }}
                primary={`${phonebook.firstName} ${phonebook.lastName}`}
                secondary={
                  <>
                    <PhoneIcon fontSize='small' />
                    {phonebook.phoneNumber}
                  </>
                }
              />
              <ListItemSecondaryAction>
                <IconButton onClick={() => handleEditClick(phonebook)}>
                  <EditIcon />
                </IconButton>
                <IconButton onClick={() => handleDeleteEntry(index, Number(phonebook.id))}>
                  <DeleteIcon color="error"/>
                </IconButton>
              </ListItemSecondaryAction>
            </ListItem>
          ))}
      </List>
    </div>
  );
};