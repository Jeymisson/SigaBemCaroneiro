/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package grupo3si.client;


import grupo3si.client.telas.TelaInicial;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class SigaBemCaroneiroView implements EntryPoint {
	
	private final SigaBemServerAsync controllerServer = GWT.create(SigaBemServer.class);
	
	public void onModuleLoad() {
		TelaInicial telaInicialComposite = new TelaInicial(controllerServer);
		telaInicialComposite.setVisible(true);
		RootPanel.get("centro").add(telaInicialComposite);
	}
}
