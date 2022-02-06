<?php
declare(strict_types=1);

namespace App\Controller;

use Cake\Http\Client;

/**
 * User Controller
 *
 * @property \App\Model\Table\UserTable $User
 * @method \App\Model\Entity\User[]|\Cake\Datasource\ResultSetInterface paginate($object = null, array $settings = [])
 */
class UserController extends AppController
{
    /**
     * Index method
     *
     * @return \Cake\Http\Response|null|void Renders view
     */
    public function index()
    {
        $user = $this->paginate($this->User);

        $this->set(compact('user'));
    }

    /**
     * View method
     *
     * @param string|null $id User id.
     * @return \Cake\Http\Response|null|void Renders view
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function view($id = null)
    {
        $user = $this->User->get($id, [
            'contain' => [],
        ]);

        $this->set(compact('user'));
    }

    /**
     * Add method
     *
     * @return \Cake\Http\Response|null|void Redirects on successful add, renders view otherwise.
     */
    public function add()
    {
        
        $user = $this->User->newEmptyEntity();

        if ($this->request->is('post')) {
        	$data = array("userName" => $_POST['username'], "password" => $_POST['password'], "userType" => $_POST['type']);
        	$http = new Client();
			$response = $http->post(
				'http://localhost:8080/register',
				json_encode($data),
				['type' => 'json']
			);

			if($response){
				// usuario é registrado na base de dados
				$this->Flash->success(__('The user has been saved.'));
				return $this->redirect(['action' => 'add']);
			} else {
				// usuario não foi registrado devido algum erro imprevisto
				$this->Flash->error(__('The user could not be saved. Please, try again.'));
				return $this->redirect(['action' => 'add']);
			}
        }

        $this->set(compact('user'));
    }

    public function login()
    {
        
        $user = $this->User->newEmptyEntity();

        if ($this->request->is('post')) {
        	$data = array("userName" => $_POST['username'], "password" => $_POST['password']);
        	$http = new Client();
			$userId = $http->post(
				'http://localhost:8080/login',
				json_encode($data),
				['type' => 'json']
			);

			if($response){
				$isTeacher = $response;
				$http = new Client();
				$response = $http->post(
					'http://localhost:8080/isTeacher',
					json_encode($isTeacher),
					['type' => 'json']
				);

				if($response){
					return $this->redirect(['action' => 'teacher']);
				} else {
					return $this->redirect(['action' => 'student']);
				}
			} else {
				$this->Flash->error(__('The user could not be saved. Please, try again.'));
			}
        }

        $this->set(compact('user'));
    }

    /**
     * Edit method
     *
     * @param string|null $id User id.
     * @return \Cake\Http\Response|null|void Redirects on successful edit, renders view otherwise.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function edit($id = null)
    {
        $user = $this->User->get($id, [
            'contain' => [],
        ]);
        if ($this->request->is(['patch', 'post', 'put'])) {
            $user = $this->User->patchEntity($user, $this->request->getData());
            if ($this->User->save($user)) {
                $this->Flash->success(__('The user has been saved.'));

                return $this->redirect(['action' => 'index']);
            }
            $this->Flash->error(__('The user could not be saved. Please, try again.'));
        }
        $this->set(compact('user'));
    }

    /**
     * Delete method
     *
     * @param string|null $id User id.
     * @return \Cake\Http\Response|null|void Redirects to index.
     * @throws \Cake\Datasource\Exception\RecordNotFoundException When record not found.
     */
    public function delete($id = null)
    {
        $this->request->allowMethod(['post', 'delete']);
        $user = $this->User->get($id);
        if ($this->User->delete($user)) {
            $this->Flash->success(__('The user has been deleted.'));
        } else {
            $this->Flash->error(__('The user could not be deleted. Please, try again.'));
        }

        return $this->redirect(['action' => 'index']);
    }
}
