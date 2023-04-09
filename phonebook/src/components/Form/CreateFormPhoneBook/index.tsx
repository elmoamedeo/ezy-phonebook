import { useContext } from 'react';
import { useForm } from 'react-hook-form';
import { zodResolver } from '@hookform/resolvers/zod';
import { z } from 'zod';
import { IPhonebook } from '@/src/@types/phonebook';

import {Button, FormControl, Input, InputLabel} from '@material-ui/core';
import {PhonebookContext} from '@/src/context/PhonebookContext';

export function CreateFormPhoneBook() {
  const {handleAddEntry} = useContext(PhonebookContext);

  const Phonebook = z.object({
    firstName: z.string()
      .min(1, {message: 'First name is necessary'})
      .max(20, {message: 'First name is too long'}),
    lastName: z.string()
      .min(1, {message: 'Last name is necessary'})
      .max(20, {message: 'Last name is too long'}),
    phoneNumber: z.string()
      .min(1, {message: 'Phone number is necessary'})
      .max(20, {message: 'Phone number is too long'})
  });

    type Phonebook = z.infer<typeof Phonebook>;

    const {
      register,
      handleSubmit,
      reset,
      formState: {errors},
    } = useForm<IPhonebook>({
      mode: 'all',
      reValidateMode: 'onChange',
      resolver: zodResolver(Phonebook),
    });

    const onSubmit = async (data: Phonebook) => {
      Phonebook.parse(data);
      handleAddEntry(data);
      reset();
    };

    return (
      <form onSubmit={handleSubmit(onSubmit)}>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="firstName">First Name</InputLabel>
          <Input
            id="firstName"
            type="text"
            {...register('firstName')}
          />
          {errors.firstName?.message && (
            <p style={{color: 'red'}}>{errors.firstName?.message}</p>
          )}
        </FormControl>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="lastName">Last Name</InputLabel>
          <Input
            id="lastName"
            type="text"
            {...register('lastName')}
          />
          {errors.lastName?.message && (
            <p style={{color: 'red'}}>{errors.lastName?.message}</p>
          )}
        </FormControl>
        <FormControl fullWidth margin="normal">
          <InputLabel htmlFor="phoneNumber">Phone Number</InputLabel>
          <Input
            id="phoneNumber"
            type="tel"
            {...register('phoneNumber')}
          />
          {errors.phoneNumber?.message && (
            <p style={{color: 'red'}}>{errors.phoneNumber?.message}</p>
          )}
        </FormControl>
        <Button
          type="submit"
          variant="contained"
          color="primary"
        >
                Add Entry
        </Button>
      </form>
    );
}